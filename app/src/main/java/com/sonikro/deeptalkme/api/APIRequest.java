package com.sonikro.deeptalkme.api;

import com.sonikro.deeptalkme.model.Token;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class APIRequest {
    private Token token;
    private Object requestObject;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Object getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Object requestObject) {
        this.requestObject = requestObject;
    }
}
