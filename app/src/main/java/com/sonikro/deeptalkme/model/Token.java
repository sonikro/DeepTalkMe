package com.sonikro.deeptalkme.model;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public class Token implements Serializable {
    private String token;
    private static final int START = 5;
    private static final int END = 15;

    public Token(String fullToken)
    {
        setFullToken(fullToken);
    }

    public String getFullToken() {
        return token;
    }

    public void setFullToken(String token) {
        this.token = token;
    }

    public String getCleanToken()
    {
        return getFullToken().substring(Token.START, Token.END);
    }
}
