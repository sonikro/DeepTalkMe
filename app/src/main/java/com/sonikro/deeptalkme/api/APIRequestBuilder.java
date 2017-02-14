package com.sonikro.deeptalkme.api;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class APIRequestBuilder {
    private DeepTalkMeAPI api;
    private APIReturn apiReturn;
    private String serviceName;
    private Object requestObject;
    private Class returnObjectClass;

    public APIRequestBuilder()
    {
        this.api = DeepTalkMeAPI.getInstance();
    }

    public void request() throws Exception
    {
        try {
            api.connect(getServiceName());
            api.writeToService(getRequestObject());
            apiReturn = api.readFromService(getReturnObjectClass());
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            api.disconnect();
        }
    }

    public APIReturn getApiReturn() {
        return apiReturn;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Object requestObject) {
        this.requestObject = requestObject;
    }

    public Class getReturnObjectClass() {
        return returnObjectClass;
    }

    public void setReturnObjectClass(Class returnObjectClass) {
        this.returnObjectClass = returnObjectClass;
    }
}
