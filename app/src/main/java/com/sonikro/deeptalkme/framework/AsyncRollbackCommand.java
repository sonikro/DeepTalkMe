package com.sonikro.deeptalkme.framework;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class AsyncRollbackCommand extends AsyncCommand {
    public AsyncRollbackCommand(ActivityCommand command, Exception exception) {
        super(command);
        setCommandException(exception);
    }

    @Override
    protected Integer doInBackground(ActivityCommand... params) {
        try {
            getCommand().rollback(getCommandException());
        }catch(Exception ex)
        {
            System.out.println("Error rolling back command " + getCommand().getCommandTitle()+ ": Exception:" +
                    " "+ex.getMessage());
            return ActivityCommand.ERROR;
        }
        return ActivityCommand.SUCCESS;
    }
}
