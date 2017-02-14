package com.sonikro.deeptalkme.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sonikro.deeptalkme.api.model.ObjectArray;
import com.sonikro.deeptalkme.model.Token;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jonathan Nagayoshi on 27/06/2016.
 */
public class DeepTalkMeAPI {
    public static final String SERVICE_URL = "http://deeptalkme.com:27099/deeptalkmeAPI/api/";
    private static final String RETURN_MESSAGE_STRING = "returnMessage";
    private static final String RETURN_OBJECT_STRING = "returnObject";
    private static final String RETURN_OBJECT_ARRAY_STRING = "objectArray";
    private static final int DEFAULT_TIMEOUT = 10000;
    private URL serviceUrl;
    private HttpURLConnection serviceConnection;
    private Gson myGson;
    private Token myAccessToken;
    private static DeepTalkMeAPI singleton;

    public static DeepTalkMeAPI getInstance()
    {
        if(singleton == null)
        {
            singleton = new DeepTalkMeAPI();
        }
        return singleton;
    }

    private DeepTalkMeAPI()
    {
        myGson = new GsonBuilder().serializeNulls().create();
    }

    public void connect(String serviceName) throws Exception
    {
        setServiceUrl(buildServiceUrl(serviceName));
        setServiceConnection(buildServiceConnection(getServiceUrl()));
    }
    public void disconnect()
    {
        if(getServiceConnection() != null)
        {
            getServiceConnection().disconnect();
        }
    }
    public URL buildServiceUrl(String service) throws MalformedURLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(SERVICE_URL);
        sb.append(service);
        URL url = new URL(sb.toString());
        return url;
    }

    public HttpURLConnection buildServiceConnection(URL serviceUrl) throws IOException
    {
        HttpURLConnection connection;
        connection = (HttpURLConnection) serviceUrl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "text/json");
        connection.setRequestProperty("Content-Type", "text/json");
        return connection;
    }

    public void writeToService(Object object) throws IOException
    {
        APIRequest request = new APIRequest();
        request.setToken(myAccessToken);
        request.setRequestObject(object);
        OutputStream writer = getServiceConnection().getOutputStream();
        String json = myGson.toJson(request, APIRequest.class);
        //System.out.println("Sending Json: "+json);
        writer.write(json.getBytes());
        writer.flush();
    }

    public APIReturn readFromService(Class returnObjectClass) throws Exception
    {
        //Gets JSON from HTTP Response
        StringBuilder stringBuilder = new StringBuilder();
        InputStream input = new BufferedInputStream(getServiceConnection().getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        System.out.println("Receiving Json : "+stringBuilder.toString());

        //Gets full JSON Response

        JSONObject fullReturnObject = new JSONObject(stringBuilder.toString());
        //Start Preparing RETURN
        APIReturn apiReturn = new APIReturn();
            //Prepare Message Return (Mandatory)
        JSONObject jsonMessage = fullReturnObject.getJSONObject(RETURN_MESSAGE_STRING);
        apiReturn.setReturnMessage(myGson.fromJson(jsonMessage.toString(), APIMessage.class));

        //Check for Return Errors
        if(apiReturn.getReturnMessage().getStatus().equals(APIMessage.STATUS_ERROR))
        {
            throw new APIException(apiReturn);
        }

        if(apiReturn.getReturnMessage().getStatus().equals(APIMessage.TOKEN_ERROR))
        {
            throw new InvalidTokenException();
        }

            //Prepare Object Return (Optional)
        if(returnObjectClass!=null)
        {
            JSONObject jsonReturnObject;
            try {
                jsonReturnObject= fullReturnObject.getJSONObject(RETURN_OBJECT_STRING);
            }catch(Exception ex)
            {
                return apiReturn;
            }

            if(returnObjectClass.isArray())
            {
                ObjectArray objectArray = myGson.fromJson(jsonReturnObject.toString(),ObjectArray.class);
                int size = objectArray.getObjectArray().length;
                JSONObject jsonObject = new JSONObject(jsonReturnObject.toString());
                JSONArray jsonArray = jsonObject.getJSONArray(RETURN_OBJECT_ARRAY_STRING);
                Object array =  Array.newInstance(returnObjectClass.getComponentType(),size);
                array = myGson.fromJson(jsonArray.toString(),array.getClass());
                apiReturn.setReturnObject(array);
            }
            else
            {
                apiReturn.setReturnObject(myGson.fromJson(jsonReturnObject.toString(),returnObjectClass));
            }

        }


        return apiReturn;
    }

    public URL getServiceUrl() {
        return serviceUrl;
    }

    protected void setServiceUrl(URL serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public HttpURLConnection getServiceConnection() {
        return serviceConnection;
    }

    protected void setServiceConnection(HttpURLConnection serviceConnection) {
        this.serviceConnection = serviceConnection;
        this.serviceConnection.setConnectTimeout(DEFAULT_TIMEOUT);
        this.serviceConnection.setReadTimeout(DEFAULT_TIMEOUT);
    }

    public Token getAccessToken() {
        return myAccessToken;
    }

    public void setAccessToken(Token myAccessToken) {
        this.myAccessToken = myAccessToken;
    }
}
