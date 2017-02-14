package com.sonikro.deeptalkme.framework.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 17/07/2016.
 */
public class ConfirmDialog{


    public static final String ASYNC_EXECUTE = "ASYNC";
    public static final String SAME_THREAD_EXECUTE = "SAME_THREAD";


    private AlertDialog mDialog;
    private ActivityCommand mYesCommand;
    private ActivityCommand mNoCommand;
    private Context mContext;
    private String mExecutionMode;
    public ConfirmDialog(Context context, String execution_mode)
    {
        mContext = context;
        mExecutionMode = execution_mode;
    }

    public void display(int textId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(textId);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                executeCommand(getYesCommand());
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                executeCommand(getNoCommand());
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    protected void executeCommand(ActivityCommand command)
    {
        if(command != null)
        {
            switch (mExecutionMode)
            {
                case ASYNC_EXECUTE:
                    command.asyncExecute();
                    break;
                case SAME_THREAD_EXECUTE:
                    command.dispatchCommand();
                    break;
            }
        }
    }
    public ActivityCommand getYesCommand() {
        return mYesCommand;
    }

    public void setYesCommand(ActivityCommand mYesCommand) {
        this.mYesCommand = mYesCommand;
    }

    public ActivityCommand getNoCommand() {
        return mNoCommand;
    }

    public void setNoCommand(ActivityCommand mNoCommand) {
        this.mNoCommand = mNoCommand;
    }

}
