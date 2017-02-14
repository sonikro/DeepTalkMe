package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.api.DeepTalkMeAPI;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.AppPreferences;
import com.sonikro.deeptalkme.framework.DeepTalkMe;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Token;
import com.sonikro.deeptalkme.model.User;
import com.sonikro.deeptalkme.model.authentication.UserAuthenticator;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public class LoginCommand extends ActivityCommand{
    private String myEmail, myPassword;
    private User user = null;
    private Token myToken;
    private String myAuthenticationMethod;
    OpenHomeCommand myHomeCommand;

    public LoginCommand(ActivityControl listener, String email, String password)
    {
        super(listener);
        myEmail = email;
        myPassword = password;
        myHomeCommand  = new OpenHomeCommand(getListener());
        myAuthenticationMethod = UserAuthenticator.AUTH_EMAIL;
    }

    public LoginCommand(ActivityControl listener, Token token)
    {
        super(listener);
        myToken = token;
        myAuthenticationMethod = UserAuthenticator.AUTH_TOKEN;
        myHomeCommand  = new OpenHomeCommand(getListener());
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        user = new User();
        user.setEmail(myEmail);
        user.setPassword(myPassword);
        user.setToken(myToken);
        user.authenticate(myAuthenticationMethod);
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Rollback Login : " + exception.getMessage());
    }

    @Override
    public void onSuccess() {
        setupTokenPreference();
        setupUserContext();
        myHomeCommand.dispatchCommand();

    }

    protected void setupTokenPreference()
    {
        AppPreferences preferences = new AppPreferences(getListener().getActivity());
        preferences.setPreference(AppPreferences.USER_TOKEN, user.getToken().getFullToken());
    }

    protected void setupUserContext()
    {
        DeepTalkMe application = (DeepTalkMe) getListener().getActivity().getMyApplication();
        application.setUser(user);
        DeepTalkMeAPI.getInstance().setAccessToken(user.getToken());
    }

}
