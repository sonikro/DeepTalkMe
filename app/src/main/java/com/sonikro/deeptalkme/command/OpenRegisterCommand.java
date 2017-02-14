package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.activity.layout.RegisterActivity;


/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class OpenRegisterCommand extends OpenCommand {

    public OpenRegisterCommand(ActivityControl myListener)
    {
        super(myListener);
        setActivityClass(RegisterActivity.class);
    }

}
