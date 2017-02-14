package com.sonikro.deeptalkme.command;

import android.app.ProgressDialog;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.CommandList;
import com.sonikro.deeptalkme.framework.SequenceCommand;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Jonathan Nagayoshi on 5/07/2016.
 */
public class SequencerCommand extends ActivityCommand {
    protected CommandList commandList;
    protected ActivityCommand currentCommand = null;
    public SequencerCommand(ActivityControl listener, CommandList commandList)
    {
        super(listener);
        this.commandList = commandList;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        Iterator<ActivityCommand> iterator = commandList.getIterator();
        Object resultObject = null;
        SequenceCommand sequenceCommand;
        int numberOfCommands = commandList.size();
        int currentCommandIndex = 0;
        while(iterator.hasNext())
        {
            currentCommand = iterator.next();

            currentCommand.setAsyncCommand(this.getAsyncCommand()); //Share ProgressDialog and stuff
            sequenceCommand = null;
            if(currentCommand instanceof SequenceCommand)
            {
                sequenceCommand = (SequenceCommand) currentCommand;
                sequenceCommand.setStarterObject(resultObject);
            }
            currentCommandIndex++;
            notifyProgress(numberOfCommands, currentCommandIndex);

            //setProgressMessage(currentCommand.getCommandMessage());

            currentCommand.execute();

            if(sequenceCommand != null)
            {
                resultObject = sequenceCommand.getResult();
            }
        }
    }

    @Override
    public void rollback(Exception exception) {
        //Starts from where the error ocurred
        int commandIndex = commandList.getCommandIndex(currentCommand);
        //First, rollback the current command
        for(int i = commandIndex; i >= 0; i--)
        {
                commandList.get(i).asyncRollback(exception);
        }
    }

    @Override
    public void onSuccess() {
        System.out.println("Command Sequence Success");
        Iterator<ActivityCommand> iterator = commandList.getIterator();
        while(iterator.hasNext())
        {
            ActivityCommand command = iterator.next();
            command.onSuccess();
        }
    }

    @Override
    protected ProgressDialog getProgressDialog() {
        ProgressDialog progressDialog = super.getProgressDialog();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return progressDialog;
    }
}
