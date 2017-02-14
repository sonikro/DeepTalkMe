package com.sonikro.deeptalkme.activity.control;

import android.os.Message;
import android.view.View;

import com.sonikro.deeptalkme.activity.layout.RegisterActivity;
import com.sonikro.deeptalkme.command.LoginCommand;
import com.sonikro.deeptalkme.command.RegisterCommand;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.Command;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class RegisterControl extends ActivityControl {
    RegisterActivity myRegisterActivity;

    public RegisterControl(RegisterActivity activity)
    {
        super(activity);
        myRegisterActivity = activity;
    }
    @Override
    public void onClick(View view) {
        if(view == myRegisterActivity.myRegisterButton)
        {
            register();
        }
    }

    protected void register()
    {
        if(myRegisterActivity.validateInput())
        {
            RegisterCommand command = new RegisterCommand(this, myRegisterActivity.txtEmail.getText().toString(),
                    myRegisterActivity.txtPassword.getText().toString());
            command.dispatchWithProgressBar( "Command in progress", "Registering user...", false);
        }
    }

    @Override
    public void onCommandSuccess(Command command) {
        super.onCommandSuccess(command);
        if(command.getClass() == LoginCommand.class)
        {
            getActivity().setResult(RegisterActivity.CLOSE_ACTIVITY);
            getActivity().finish();
        }
    }
}
