package com.sonikro.deeptalkme.framework;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.sonikro.deeptalkme.activity.control.ActivityControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jonathan Nagayoshi on 12/05/2016.
 */
public abstract class ActivityCommand implements Command{

    public static final int ERROR = 4;
    public static final int SUCCESS = 0;
    protected ActivityControl myListener;
    protected boolean executed;
    private Bundle data;
    private AsyncCommand myAsyncCommand;
    private String commandTitle;
    private String commandMessage;
    private boolean commandCancelable;
    private Object mCaller; //This is used for callback methods
    private Method mCallbackMethod;
    private boolean mNoProgressDialog;
    public ActivityCommand()
    {

    }

    public ActivityCommand(ActivityControl listener)
    {
        setListener(listener);

    }

    public void  setCallbackMethod(Object caller, String method_name) throws NoSuchMethodException
    {
        mCaller = caller;
        Class<?> callerClass = mCaller.getClass();
        mCallbackMethod = callerClass.getMethod(method_name, Object.class);
    }

    public void callback(Object object)
    {
        try {
            mCallbackMethod.invoke(mCaller,object);
        }catch(Exception ex)
        {
            ex.printStackTrace(System.out);
        }

    }
    public void dispatchCommand()
    {
        getListener().onCommand(this);
    }

    @Override
    public void execute() throws Exception {
        if(wasExecuted())
        {
            throw new AlreadyExecutedException("Command already executed");
        }
        setExecuted(true);
    }

    public void dispatchWithProgressBar(String title, String message, boolean cancelable)
    {
        setAsyncCommand(new AsyncCommand(this));
        commandCancelable = cancelable;
        setCommandMessage(message);
        setCommandTitle(title);
        getAsyncCommand().execute(this);
    }

    public void asyncExecute()
    {
        setAsyncCommand(new AsyncCommand(this));
        getAsyncCommand().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this);
    }
    public void asyncRollback(Exception exception)
    {
        AsyncRollbackCommand async = new AsyncRollbackCommand(this,exception);
        async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
    }

    public void cancelCommand()
    {
        if(myAsyncCommand != null)
        {
            myAsyncCommand.cancel(true);
        }
    }
    @Override
    public boolean wasExecuted() {
        return isExecuted();
    }

    protected boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }

    public ActivityControl getListener() {
        return myListener;
    }

    public void setListener(ActivityControl myListener) {
        this.myListener = myListener;
    }

    protected void notifyProgress(Integer progress)
    {
        if(getAsyncCommand() != null)
        {
            getAsyncCommand().updateProgress(progress);
        }

    }

    protected void setProgressMessage(String message)
    {
        if(getAsyncCommand() != null)
        {
            getAsyncCommand().getProgressDialog().setMessage(message);
        }
    }

    protected void notifyProgress(Integer totalNumber, Integer currentNumber)
    {
        int percentage = 0;
        float floatPerc =  ( (float) currentNumber / totalNumber );
        floatPerc *= 100;
        percentage = (int) floatPerc;
        notifyProgress(percentage);
    }

    public AsyncCommand getAsyncCommand() {
        return myAsyncCommand;
    }

    public void setAsyncCommand(AsyncCommand myAsyncCommand) {
        this.myAsyncCommand = myAsyncCommand;
    }


    protected ProgressDialog getProgressDialog() //This is called from AsyncCommand
    {
        if(!isNoProgressDialog())
        {
            ProgressDialog progressDialog = new ProgressDialog(getListener().getActivity());
            progressDialog.setTitle(getCommandTitle());
            progressDialog.setMessage(getCommandMessage());
            progressDialog.setCancelable(commandCancelable);
            return progressDialog;
        }
        return null;
    }

    public String getCommandTitle() {
        return commandTitle;
    }

    public void setCommandTitle(String commandTitle) {
        this.commandTitle = commandTitle;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public void setCommandMessage(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    public boolean isNoProgressDialog() {
        return mNoProgressDialog;
    }

    public void setNoProgressDialog(boolean mNoProgressDialog) {
        this.mNoProgressDialog = mNoProgressDialog;
    }
}
