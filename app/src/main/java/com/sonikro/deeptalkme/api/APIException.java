package com.sonikro.deeptalkme.api;

/**
 * Created by Jonathan Nagayoshi on 28/06/2016.
 */
public class APIException extends Exception {
    public APIException(APIReturn apiReturn)
    {
        super(apiReturn.getReturnMessage().toString());
    }

    public APIReturn getApiReturn()
    {
        APIReturn apiReturn = new APIReturn();
        APIMessage apiMessage = new APIMessage();
        apiMessage.setMessage(getMessage());
        apiMessage.setStatus(APIMessage.STATUS_ERROR);
        apiReturn.setReturnMessage(apiMessage);
        return apiReturn;
    }
}
