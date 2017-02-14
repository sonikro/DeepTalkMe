package com.sonikro.deeptalkme.api;

/**
 * Created by Jonathan Nagayoshi on 28/06/2016.
 */
public class APIReturn {

    private APIMessage returnMessage;
    private Object returnObject;

    public APIMessage getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(APIMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
