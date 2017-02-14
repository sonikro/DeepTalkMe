package com.sonikro.deeptalkme.activity.control;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sonikro.deeptalkme.activity.layout.SuperActivity;
import com.sonikro.deeptalkme.api.InvalidTokenException;
import com.sonikro.deeptalkme.command.LogoutCommand;
import com.sonikro.deeptalkme.framework.Command;
import com.sonikro.deeptalkme.framework.CommandListener;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public abstract class ActivityControl implements CommandListener, View.OnClickListener{
    private SuperActivity myActivity;


    public ActivityControl()
    {

    }
    public ActivityControl(SuperActivity activity)
    {
        setActivity(activity);
    }

    @Override
    public void onCommand(Command command) {
        Exception exception = null;
        try {
            command.execute();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
            exception = ex;
            command.rollback(ex);
            myActivity.showSnackbar(ex.getMessage(), Snackbar.LENGTH_LONG);
            ex.printStackTrace(System.out);
        }
        if(exception == null)
        {
            command.onSuccess();
        }
    }

    @Override
    public void onCommandSuccess(Command command) {
        command.onSuccess();
    }

    @Override
    public void onCommandError(Command command, Exception exception) {
        myActivity.showSnackbar(exception.getMessage(), Snackbar.LENGTH_LONG);
        exception.printStackTrace(System.out);
        command.rollback(exception);
        if(exception instanceof InvalidTokenException)
        {
            forceLogout();
        }
        exception.printStackTrace(System.out);
    }

    protected void forceLogout()
    {
        LogoutCommand command = new LogoutCommand(this);
        command.dispatchCommand();
    }
    public SuperActivity getActivity() {
        return myActivity;
    }

    public void setActivity(SuperActivity myActivity) {
        this.myActivity = myActivity;
    }

    protected void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) myActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = myActivity.getCurrentFocus();
        if(view == null)
        {
            view = new View(myActivity);
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
