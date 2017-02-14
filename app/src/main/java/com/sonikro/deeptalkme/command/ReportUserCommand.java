package com.sonikro.deeptalkme.command;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.framework.dialog.ViewDialog;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 15/07/2016.
 */
public class ReportUserCommand extends ActivityCommand implements SequenceCommand,
        ViewDialog.ViewDialogListener{
    private Discussion mDiscussion;
    private ViewDialog mViewDialog;
    public ReportUserCommand(ActivityControl listener)
    {
        super(listener);
    }

    public ReportUserCommand(ActivityControl listener, Discussion discussion)
    {
        this(listener);
        mDiscussion = discussion;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mViewDialog = new ViewDialog(getListener().getActivity());
        mViewDialog.display(R.layout.report_user_layout,this);
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Rollback OpenReportUserDialog");
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public Object getResult() {
        return null;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Discussion)
        {
            mDiscussion = (Discussion) object;
        }
    }

    @Override
    public void onPositiveButton(View inflatedView) {
        EditText txtClaim = (EditText) inflatedView.findViewById(R.id.report_claim);
        DynamicCommand dynamicCommand = new DynamicCommand(getListener());
        dynamicCommand.setCaller(mDiscussion);
        dynamicCommand.setMethodName("reportUser");
        dynamicCommand.setParameterTypes(String.class);
        dynamicCommand.setParameters(txtClaim.getText().toString());
        dynamicCommand.setOnSuccessCallback(this,"onReportSuccess");
        dynamicCommand.setRollbackCallback(this,"onReportError");
        dynamicCommand.asyncExecute();

    }

    public void onReportSuccess()
    {
        getListener().getActivity().showSnackbar("Your report was successfully submitted", Snackbar.LENGTH_LONG);
    }

    public void onReportError(Exception ex)
    {
        getListener().getActivity().showSnackbar(ex.getMessage(),Snackbar.LENGTH_LONG);
    }

    @Override
    public void onNegativeButton(View inflatedView) {
        mViewDialog.dismiss();
    }

    @Override
    public void onViewInflate(View inflatedView) {

    }
}
