package com.sonikro.deeptalkme.model.authentication;

import com.sonikro.deeptalkme.DAO.UserDAO;
import com.sonikro.deeptalkme.model.Token;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public abstract class UserAuthenticator {
    public static final String AUTH_EMAIL = "EMAIL";
    public static final String AUTH_TOKEN = "TOKEN";
    public static UserAuthenticator factory(String authenticationMethod) throws Exception
    {
        switch(authenticationMethod)
        {
            case AUTH_EMAIL:
                return new UserEmailAuthenticator();
            case AUTH_TOKEN:
                return new UserTokenAuthenticator();
            default:
                throw new Exception("Invalid Authentication method");
        }
    }

    public abstract User authenticate(User user) throws Exception;

}
