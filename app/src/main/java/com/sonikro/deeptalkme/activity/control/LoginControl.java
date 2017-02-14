package com.sonikro.deeptalkme.activity.control;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sonikro.deeptalkme.activity.layout.LoginActivity;
import com.sonikro.deeptalkme.command.LoginCommand;
import com.sonikro.deeptalkme.command.OpenRegisterCommand;
import com.sonikro.deeptalkme.command.RequestNewPasswordCommand;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.AppPreferences;
import com.sonikro.deeptalkme.framework.Command;
import com.sonikro.deeptalkme.model.Token;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 16/05/2016.
 */
public class LoginControl extends ActivityControl{
    LoginActivity loginActivity;

    public LoginControl(LoginActivity activity)
    {
        super(activity);
        loginActivity = activity;
        checkAlreadyLoggedIn();
    }

    protected void checkAlreadyLoggedIn()
    {
        AppPreferences preferences = new AppPreferences(loginActivity);
        String token = preferences.getPreference(AppPreferences.USER_TOKEN,"");
        if(!token.equals(""))
        {
            LoginCommand loginCommand = new LoginCommand(this,new Token(token));
            loginCommand.dispatchWithProgressBar("Attempting Login...", "Authenticating User", true);
        }
    }

    @Override
    public void onClick(View view) {

        if(view == loginActivity.myLoginButton)
        {
            loginButton();
        }
        if(view == loginActivity.mySignUpButton)
        {
            signUp();
        }
        if(view == loginActivity.myRequestNewPasswordButton)
        {
            requestNewPassword();
        }
    }

    protected  void requestNewPassword() {
        if(loginActivity.validateRequestPasswordFields())
        {
            User user = new User();
            user.setEmail(loginActivity.myEmail.getText().toString());
            RequestNewPasswordCommand command = new RequestNewPasswordCommand(this,user);
            command.dispatchWithProgressBar("Requesting...","Requesting new password",false);
        }
    }

    protected void loginButton()
    {
        if(loginActivity.validateFields())
        {
            attemptLogin();
        }
    }

    protected void signUp()
    {
        OpenRegisterCommand command = new OpenRegisterCommand(this);
        command.dispatchCommand();

    }

    protected void attemptLogin()
    {
        LoginCommand command = new LoginCommand(this, loginActivity.myEmail.getText().toString(),
                loginActivity.myPassword.getText().toString());

        command.dispatchWithProgressBar("Attempting Login...", "Authenticating User", true);

    }

    @Override
    public void onCommandSuccess(Command command) {
        super.onCommandSuccess(command);
        if(command.getClass() == LoginCommand.class)
        {
            getActivity().finish();
        }
    }
}
