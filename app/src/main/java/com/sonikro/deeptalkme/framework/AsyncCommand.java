package com.sonikro.deeptalkme.framework;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class AsyncCommand extends AsyncTask<ActivityCommand, Integer, Integer> implements DialogInterface.OnCancelListener {
    private Exception commandException;
    private ActivityCommand command;
    private ProgressDialog myProgressDialog;

    public AsyncCommand(ActivityCommand command)
    {
        super();
        this.setCommand(command);
    }
    @Override
    protected void onPreExecute() {
        setProgressDialog(getCommand().getProgressDialog());
        if(getProgressDialog() != null)
        {
            getProgressDialog().setOnCancelListener(this);
            getProgressDialog().show();
        }


    }

    @Override
    protected Integer doInBackground(ActivityCommand... params) {
        setCommand(params[0]);
        try {
            getCommand().execute();
        }catch(Exception ex)
        {
            setCommandException(ex);
            return ActivityCommand.ERROR;
        }
        return ActivityCommand.SUCCESS;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        dismissProgressDialog();
        if(getCommandException() != null)
        {
            getCommand().getListener().onCommandError(getCommand(), getCommandException());
        }
        else
        {
            getCommand().getListener().onCommandSuccess(getCommand());
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        getProgressDialog().setProgress(values[0]);
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        this.cancel(true);
    }

    @Override
    protected void onCancelled(Integer integer) {
        getCommand().getListener().onCommandError(getCommand(), new Exception("Command Aborted"));
    }

    protected void dismissProgressDialog()
    {
        if((getProgressDialog() != null) && getProgressDialog().isShowing())
        {
            getProgressDialog().dismiss();
        }
    }

    public ProgressDialog getProgressDialog() {
        return myProgressDialog;
    }

    public void setProgressDialog(ProgressDialog myProgressDialog) {
        this.myProgressDialog = myProgressDialog;
    }

    public void updateProgress(Integer progress)
    {
        publishProgress(progress);
    }

    public void showProgressDialog()
    {
        getProgressDialog().show();
    }

    protected ActivityCommand getCommand() {
        return command;
    }

    protected void setCommand(ActivityCommand command) {
        this.command = command;
    }

    protected Exception getCommandException() {
        return commandException;
    }

    protected void setCommandException(Exception commandException) {
        this.commandException = commandException;
    }
}
