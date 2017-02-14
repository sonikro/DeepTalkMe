package com.sonikro.deeptalkme.activity.layout;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;


import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.ActivityControl;

import com.sonikro.deeptalkme.activity.control.ActivityControlFactory;
import com.sonikro.deeptalkme.framework.DeepTalkMe;
import com.sonikro.deeptalkme.framework.UncaughtHandlerException;


/**
 * Created by Jonathan Nagayoshi on 28/05/2016.
 */
public abstract class SuperActivity extends AppCompatActivity {
    public static final int EXIT_APPLICATION = 1;
    public static final int DO_NOT_EXIT      = 0;
    protected ActivityControl myController;
    protected Toolbar myToolbar;
    protected View mySnackbarView;
    protected DeepTalkMe myApplication;

    protected void initializeComponents()
    {
        myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        setMyApplication((DeepTalkMe) getApplication());
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtHandlerException());
    }

    protected void initializeController()
    {
        setMyController(ActivityControlFactory.factory(this));
    }

    protected abstract void setListeners();


    public ActivityControl getController() {
        return myController;
    }

    public void setMyController(ActivityControl myController) {
        this.myController = myController;
    }

    public Toolbar getMyToolbar() {
        return myToolbar;
    }

    public void setToolbar(Toolbar myToolbar) {
        this.myToolbar = myToolbar;
    }

    public View getSnackbarView() {
        return mySnackbarView;
    }

    public void setSnackbarView(View view) {
        this.mySnackbarView = view;
    }

    public void showSnackbar(String message, int length)
    {
        Snackbar snackbar = Snackbar.make(getSnackbarView(),message,length);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        view.setLayoutParams(params);
        snackbar.show();
    }

    public DeepTalkMe getMyApplication() {
        return myApplication;
    }

    public void setMyApplication(DeepTalkMe myApplication) {
        this.myApplication = myApplication;
    }

    public void onBackPressed(int action)
    {
        if(action == EXIT_APPLICATION)
        {
            super.onBackPressed();
        }
    }
    @Override
    public void onBackPressed() {
        BackDialogListener dialogListener = new BackDialogListener(this);

        if(isTaskRoot())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.are_you_sure_exit);
            builder.setPositiveButton(R.string.yes, dialogListener);
            builder.setNegativeButton(R.string.no, dialogListener );
            builder.setCancelable(false);
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    protected class BackDialogListener implements DialogInterface.OnClickListener {
        private SuperActivity activity;

        public BackDialogListener(SuperActivity activity)
        {
            this.activity = activity;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which)
            {
                case DialogInterface.BUTTON_POSITIVE:
                    this.activity.onBackPressed(SuperActivity.EXIT_APPLICATION);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
            }
        }
    }
}
