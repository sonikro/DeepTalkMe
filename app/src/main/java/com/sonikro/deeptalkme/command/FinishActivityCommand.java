package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;

/**
 * Created by Jonathan Nagayoshi on 11/07/2016.
 */
public class FinishActivityCommand extends ActivityCommand implements SequenceCommand {

    public FinishActivityCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Finishing Activity: ");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        getListener().getActivity().finish();
    }

    @Override
    public Object getResult() {
        return null;
    }

    @Override
    public void setStarterObject(Object object) {

    }
}
