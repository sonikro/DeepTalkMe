package com.sonikro.deeptalkme.api;

/**
 * Created by Jonathan Nagayoshi on 28/06/2016.
 */
public class APIMessage {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";
    public static final String TOKEN_ERROR = "TOKEN_ERROR";

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
