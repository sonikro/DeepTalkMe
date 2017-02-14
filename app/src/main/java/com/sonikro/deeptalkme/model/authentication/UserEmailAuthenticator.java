package com.sonikro.deeptalkme.model.authentication;

import com.sonikro.deeptalkme.DAO.UserDAO;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class UserEmailAuthenticator extends UserAuthenticator {
    @Override
    public User authenticate(User user) throws Exception {
        return (UserDAO.getInstance().authenticateUser(user));
    }
}
