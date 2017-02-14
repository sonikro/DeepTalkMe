package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.UpdateUserAPI;
import com.sonikro.deeptalkme.model.Token;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public class UserDAO extends APIDAO{
    public static final String AUTH_USER_BY_PASSWORD = "AuthUserByPassword";
    public static final String REGISTER_USER = "RegisterUser";
    public static final String AUTH_USER_BY_TOKEN = "AuthUserByToken";
    public static final String UPDATE_USER = "UpdateUser";
    public static final String REQUEST_NEW_PASSWORD = "ForgotUserPassword";
    public static final String GET_USER_FRIENDS = "GetUserFriends";
    public static final String GET_USER_DETAILS = "GetUserDetails";
    private static UserDAO instance = null;

    public UserDAO()
    {
        super();
    }

    public static UserDAO getInstance()
    {
        if(instance == null)
        {
            instance = new UserDAO();
        }
        return instance;
    }

    public User authenticateUser(User user) throws Exception
    {
        User returnUser;
       APIReturn apiReturn = null;
        try {
            getApi().connect(AUTH_USER_BY_PASSWORD);
            getApi().writeToService(user);
            apiReturn = getApi().readFromService(User.class);
            returnUser = (User) apiReturn.getReturnObject();
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
        return returnUser;

    }

    public void registerUser(User user) throws Exception
    {
        APIReturn apiReturn = null;

        try {
            getApi().connect(REGISTER_USER);
            getApi().writeToService(user);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }
    }

    public User authenticateUserUsingToken(Token token) throws Exception
    {
        APIReturn apiReturn = null;
        User returnUser = null;
        try {
            getApi().connect(AUTH_USER_BY_TOKEN);
            getApi().writeToService(token);
            apiReturn = getApi().readFromService(User.class);
            returnUser = (User) apiReturn.getReturnObject();
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
        return returnUser;
    }

    public void updateUserSettings(User user, String new_password) throws Exception
    {
        APIReturn apiReturn = null;
        UpdateUserAPI updateUser = new UpdateUserAPI(user);
        updateUser.setNew_password(new_password);
        try {
            getApi().connect(UPDATE_USER);
            getApi().writeToService(updateUser);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public void requestNewPassword(User user) throws Exception
    {
        APIReturn apiReturn = null;
        try {
            getApi().connect(REQUEST_NEW_PASSWORD);
            getApi().writeToService(user);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public User[] getFriends(User user) throws Exception{
        APIReturn apiReturn;
        User[] mFriends = null;
        try {
            getApi().connect(GET_USER_FRIENDS);
            getApi().writeToService(user);
            apiReturn = getApi().readFromService(User[].class);
            mFriends = (User[]) apiReturn.getReturnObject();
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
        return mFriends;
    }
}
