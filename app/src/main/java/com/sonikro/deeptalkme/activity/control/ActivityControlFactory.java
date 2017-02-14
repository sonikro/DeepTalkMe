package com.sonikro.deeptalkme.activity.control;

import com.sonikro.deeptalkme.activity.layout.SuperActivity;
import com.sonikro.deeptalkme.activity.layout.ChatActivity;
import com.sonikro.deeptalkme.activity.layout.ContactsActivity;
import com.sonikro.deeptalkme.activity.layout.HomeActivity;
import com.sonikro.deeptalkme.activity.layout.LoginActivity;
import com.sonikro.deeptalkme.activity.layout.RegisterActivity;
import com.sonikro.deeptalkme.activity.layout.UpdateUserActivity;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class ActivityControlFactory {
    public static ActivityControl factory(SuperActivity activity)
    {
        Class activityClass = activity.getClass();
        if(activityClass == LoginActivity.class)
        {
            return new LoginControl((LoginActivity)activity);
        }

        if(activityClass == HomeActivity.class)
        {
            return new HomeControl((HomeActivity)activity);
        }

        if(activityClass == RegisterActivity.class)
        {
            return new RegisterControl((RegisterActivity)activity);
        }

        if(activityClass == UpdateUserActivity.class)
        {
            return new UpdateUserControl((UpdateUserActivity)activity);
        }

        if(activityClass == ChatActivity.class)
        {
            return new ChatControl((ChatActivity)activity);
        }
        if(activityClass == ContactsActivity.class)
        {
            return new ContactsControl((ContactsActivity)activity);
        }
        return null;
    }
}
