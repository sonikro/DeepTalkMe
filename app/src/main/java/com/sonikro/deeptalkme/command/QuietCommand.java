package com.sonikro.deeptalkme.command;

import android.app.ProgressDialog;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.Command;
import com.sonikro.deeptalkme.framework.SequenceCommand;

/**
 * Created by Jonathan Nagayoshi on 11/07/2016.
 */
public class QuietCommand extends ActivityCommand implements SequenceCommand {
    private Command mCommand;
    public QuietCommand(ActivityControl listener) {
        super(listener);
    }

    public QuietCommand(ActivityControl listener, Command sequencer)
    {
        super(listener);
        mCommand = sequencer;

    }
    @Override
    public void execute() throws Exception {
        try {
            mCommand.execute();
        }catch(Exception ex)
        {
            //Do nothing, because this is a QuietCommand
            //Whoever called it, does not want to be disturbed
        }
    }

    @Override
    public void rollback(Exception exception) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public Object getResult() {
        return mCommand;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Command)
        {
            mCommand = (Command) object;
        }
    }

    @Override
    protected ProgressDialog getProgressDialog() {
        return null;
    }
}
