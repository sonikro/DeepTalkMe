package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.activity.layout.UpdateUserActivity;

/**
 * Created by Jonathan Nagayoshi on 20/06/2016.
 */
public class OpenSettingsCommand extends OpenCommand {

    public OpenSettingsCommand(ActivityControl listener)
    {
        super(listener);
        setActivityClass(UpdateUserActivity.class);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        getListener().getActivity().finish();
    }
}
