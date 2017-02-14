package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.activity.layout.LoginActivity;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.AppPreferences;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class LogoutCommand extends ActivityCommand {

    public LogoutCommand(ActivityControl myListener)
    {
        super(myListener);
    }
    @Override
    public void execute() throws Exception {
        super.execute();
        AppPreferences preferences = new AppPreferences(getListener().getActivity());
        preferences.setPreference(AppPreferences.USER_TOKEN,"");
        System.out.println("Logging Out");
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Logout Error");
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(getListener().getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getListener().getActivity().startActivity(intent);
        getListener().getActivity().finish();
    }
}
