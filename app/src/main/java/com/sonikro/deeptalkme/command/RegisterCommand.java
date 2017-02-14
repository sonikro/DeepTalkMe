package com.sonikro.deeptalkme.command;

import android.os.Bundle;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class RegisterCommand extends ActivityCommand{
    private String myEmail, myPassword;
    private User myUser;
    public RegisterCommand(ActivityControl myListener, String email, String password)
    {
        super(myListener);
        myEmail = email;
        myPassword = password;

    }
    @Override
    public void execute() throws Exception {
        super.execute();
        Thread.sleep(2000);
        myUser = new User();
        myUser.setEmail(myEmail);
        myUser.setPassword(myPassword);
        myUser.register();

        Bundle data = new Bundle();
        data.putString("username", myUser.getName());
        data.putString("email", myUser.getEmail());
        setData(data);

    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Register Command Rollback: "+exception.getMessage());
}
    @Override
    public void onSuccess() {
        LoginCommand loginCommand = new LoginCommand(getListener(), myEmail, myPassword);
        loginCommand.dispatchWithProgressBar("Command in progress...","Logging in...", false);
    }

}
