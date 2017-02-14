package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.api.APIRequestBuilder;
import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class APIRequestCommand extends ActivityCommand {
    private APIRequestBuilder myRequest;
    private APIReturn myReturn;
    private APIRequestListener myApiListener;

    public interface APIRequestListener{
        public void onRequestFinish(APIRequestBuilder requestBuilder, APIReturn apiReturn);
    }
    public APIRequestCommand(ActivityControl listener, APIRequestBuilder request, APIRequestListener apiListener)
    {
        super(listener);
        this.myRequest = request;
        this.myApiListener = apiListener;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        myRequest.request();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("APIRequestCommand Error : "+exception.getMessage());
    }

    @Override
    public void onSuccess() {
        myReturn = myRequest.getApiReturn();
        myApiListener.onRequestFinish(myRequest,myReturn);
    }

    public APIReturn getReturn() {
        return myReturn;
    }
}
