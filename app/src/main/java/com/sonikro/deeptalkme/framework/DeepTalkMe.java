package com.sonikro.deeptalkme.framework;

import android.app.Application;

import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 20/06/2016.
 */
public class DeepTalkMe extends Application {
    public static final String PROCESS_NAME = "com.sonikro.deeptalkme";
    private User myUser;
    private Discussion myDiscussion;

    public User getUser() {
        return myUser;
    }

    public void setUser(User myUser) {
        this.myUser = myUser;
    }

    public Discussion getDiscussion() {
        return myDiscussion;
    }

    public void setDiscussion(Discussion myDiscussion) {
        this.myDiscussion = myDiscussion;
    }
}
