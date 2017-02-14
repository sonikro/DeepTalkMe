package com.sonikro.deeptalkme.command;

import android.support.design.widget.Snackbar;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 */
public class RequestNewPasswordCommand extends ActivityCommand {
    private User mUser;
    public RequestNewPasswordCommand(ActivityControl listener, User user)
    {
        super(listener);
        mUser = user;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mUser.requestNewPassword();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Requesting new password");
    }

    @Override
    public void onSuccess() {
        getListener().getActivity().showSnackbar("A new password has been sent to your e-mail", Snackbar.LENGTH_LONG);
    }
}
