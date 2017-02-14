package com.sonikro.deeptalkme.command;

import android.app.ProgressDialog;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.AlreadyExecutedException;
import com.sonikro.deeptalkme.framework.CommandList;

import java.util.Iterator;

/**
 * Created by Jonathan Nagayoshi on 23/07/2016.
 */
public class CommandServiceCommand extends ActivityCommand {
    private static int INTERVAL_BETWEEN_RUNS = 2000;
    private CommandList mCommandWaitList;
    private CommandList mCommandList;
    private CommandList mRemoveCommandList;
    private int mIntervalBetweenCommands = 0;

    public CommandServiceCommand(ActivityControl listener)
    {
        super(listener);
        mCommandList = new CommandList();
        mRemoveCommandList = new CommandList();
        mCommandWaitList = new CommandList();
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        while(true)
        {
            Thread.sleep(INTERVAL_BETWEEN_RUNS);
            mRemoveCommandList.clear();
            mCommandList.addAll(mCommandWaitList);
            mCommandWaitList.clear();
            Iterator<ActivityCommand> iterator = mCommandList.getIterator();
            while(iterator.hasNext())
            {
                ActivityCommand command = iterator.next();
                try {
                    command.execute();
                    mRemoveCommandList.add(command); //Add this command to the removed list.
                    System.out.println("Command added to remove list");
                }catch(AlreadyExecutedException exi)
                {
                    mRemoveCommandList.add(command); //Add this command to the removed list.
                    System.out.println("Command already executed.. Removing from list.");
                }
                catch(Exception ex)
                {
                    //Error... Stays in the queue
                    command.setExecuted(false); //Rollback flag to allow reprocessing
                    System.out.println("Command Service Error : ");
                    ex.printStackTrace(System.out);
                    break;
                }
                Thread.sleep(getIntervalBetweenCommands());
            }

            removeSuccessfullCommands();
        }
    }

    private void removeSuccessfullCommands() {
        if(mRemoveCommandList.size() > 0) {
            mCommandList.removeAll(mRemoveCommandList);
            System.out.println("Successfull commands removed from service");
        }
    }

    @Override
    public void rollback(Exception exception) {

    }

    @Override
    public void onSuccess() {

    }

    public void addCommand(ActivityCommand command)
    {
        mCommandWaitList.add(command);
        System.out.println("Command added to service wait list");
    }

    @Override
    protected ProgressDialog getProgressDialog() {
        return null;
    }

    public int getIntervalBetweenCommands() {
        return mIntervalBetweenCommands;
    }

    public void setIntervalBetweenCommands(int mIntervalBetweenCommands) {
        this.mIntervalBetweenCommands = mIntervalBetweenCommands;
    }
}
