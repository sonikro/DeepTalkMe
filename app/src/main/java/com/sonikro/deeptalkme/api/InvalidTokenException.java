package com.sonikro.deeptalkme.api;

/**
 * Created by Jonathan Nagayoshi on 10/07/2016.
 */
public class InvalidTokenException extends Exception {
    public InvalidTokenException()
    {
        super("Your token is no longer valid. Please log in again");
    }
}
