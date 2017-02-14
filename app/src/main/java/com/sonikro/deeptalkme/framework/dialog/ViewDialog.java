package com.sonikro.deeptalkme.framework.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.sonikro.deeptalkme.R;

/**
 * Created by Jonathan Nagayoshi on 16/07/2016.
 */
public class ViewDialog{
    private Context mRootContext;
    private View mPromptView;
    private AlertDialog mAlertDialog;
    private int mDialogLayout;
    private ViewDialogListener mListener;




    public interface ViewDialogListener
    {
        public void onPositiveButton(View inflatedView);
        public void onNegativeButton(View inflatedView);
        public void onViewInflate(View inflatedView);
    }

    public ViewDialog(Context rootContext)
    {
        mRootContext = rootContext;
    }

    public void display(int dialogLayout, ViewDialogListener listener)
    {
        mDialogLayout = dialogLayout;
        mListener = listener;
        setupInflater();
        setupAlertDialog();
        mAlertDialog.show();
    }

    protected void setupInflater()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(mRootContext);
        mPromptView = layoutInflater.inflate(mDialogLayout,null);
        if(mListener != null)
        {
            mListener.onViewInflate(mPromptView);
        }
    }

    protected void setupAlertDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mRootContext);
        dialogBuilder.setView(mPromptView);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onPositiveButton(mPromptView);
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onNegativeButton(mPromptView);
            }
        });
        mAlertDialog = dialogBuilder.create();
    }

    public void dismiss() {
        if(mAlertDialog!=null)
        {
            mAlertDialog.dismiss();
        }
    }

}
