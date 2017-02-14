package com.sonikro.deeptalkme.api.model;

import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 28/06/2016.
 */
public class UpdateUserAPI {
    private User user;
    private String new_password;

    public UpdateUserAPI(User user)
    {
        setUser(user);
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
