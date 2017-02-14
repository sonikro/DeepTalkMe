package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public abstract class OpenCommand extends ActivityCommand {
    private Class activityClass;
    protected Intent intent;

    public OpenCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        intent = new Intent(getListener().getActivity(), getActivityClass());
        //myListener.getActivity().startActivityForResult(intent, 0);
        getListener().getActivity().startActivity(intent);
    }

    @Override
    public void onSuccess() {
        System.out.println(getActivityClass() + " called successfully");
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error calling "+getActivityClass() + " Exception : "+exception.getMessage());
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
