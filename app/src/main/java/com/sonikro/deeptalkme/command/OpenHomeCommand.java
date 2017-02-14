package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.layout.HomeActivity;
import com.sonikro.deeptalkme.activity.control.ActivityControl;

/**
 * Created by Jonathan Nagayoshi on 23/05/2016.
 */
public class OpenHomeCommand extends OpenCommand {

    public OpenHomeCommand(ActivityControl listener)
    {
        super(listener);
        setActivityClass(HomeActivity.class);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        intent = new Intent(getListener().getActivity(), getActivityClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getListener().getActivity().startActivity(intent);
        getListener().getActivity().finish();
    }
}
