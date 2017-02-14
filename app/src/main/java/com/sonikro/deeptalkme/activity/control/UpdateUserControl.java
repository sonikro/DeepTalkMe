package com.sonikro.deeptalkme.activity.control;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.sonikro.deeptalkme.DAO.LanguageDAO;
import com.sonikro.deeptalkme.DAO.SubjectDAO;
import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.layout.UpdateUserActivity;
import com.sonikro.deeptalkme.api.APIRequestBuilder;
import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.ObjectArray;
import com.sonikro.deeptalkme.command.APIRequestCommand;
import com.sonikro.deeptalkme.command.UpdateUserCommand;
import com.sonikro.deeptalkme.framework.dialog.DialogItem;
import com.sonikro.deeptalkme.framework.dialog.ObjectSelectListDialog;
import com.sonikro.deeptalkme.model.Language;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.User;

import java.util.Arrays;

/**
 * Created by Jonathan Nagayoshi on 20/06/2016.
 */
public class UpdateUserControl extends DrawerControl implements ObjectSelectListDialog.ObjectSelectListListener,
                                                                        APIRequestCommand.APIRequestListener{
    private UpdateUserActivity myUpdateUserActivity;
    private ObjectSelectListDialog languageDialog, subjectDialog;
    private User myTempUser;
    public UpdateUserControl(UpdateUserActivity activity)
    {
        super(activity);
        myUpdateUserActivity = activity;
        myTempUser = new User(activity.getMyApplication().getUser());
    }


    @Override
    public void onClick(View view) {
        if(view == myUpdateUserActivity.btnSave)
        {
            saveData();
        }

        if(view == myUpdateUserActivity.btnLanguages)
        {
            languagesDialog();
        }

        if(view == myUpdateUserActivity.btnSubjects)
        {
            subjectsDialog();
        }
    }

    protected void saveData()
    {
        if(myUpdateUserActivity.validateInput())
        {
            updateTempUserFromActivity();
            UpdateUserCommand command = new UpdateUserCommand(this, myTempUser,
                    myUpdateUserActivity.txtNewPassword.getText().toString());
            command.dispatchWithProgressBar( "Command in progress...", "Updating Data", false);
        }
    }

    private void updateTempUserFromActivity() {
        myTempUser.setName(myUpdateUserActivity.txtName.getText().toString());
        if(!myUpdateUserActivity.txtNewPassword.getText().toString().isEmpty())
        {
            myTempUser.setPassword(myUpdateUserActivity.txtCurrentPassword.getText().toString());
        }
    }

    protected void languagesDialog()
    {
        APIRequestBuilder request = LanguageDAO.getInstance().getAllLanguagesRequest();
        APIRequestCommand command = new APIRequestCommand(this, request, this);
        command.dispatchWithProgressBar( "Executing Command", "Fetching Languages...", false);
    }

    protected void subjectsDialog()
    {
        APIRequestBuilder requestBuilder = SubjectDAO.getInstance().getAllSubjectsRequest();
        APIRequestCommand apiCommand = new APIRequestCommand(this, requestBuilder, this);
        apiCommand.dispatchWithProgressBar("Executing Command", "Fetching Subjects...", false);
    }

    protected void displaySubjectsDialog(Subject[] subjects)
    {
        subjectDialog = new ObjectSelectListDialog(getActivity(),subjects,myTempUser.getFavoriteSubjects(),this);
        subjectDialog.show(getActivity().getResources().getString(R.string.select_subjects));
    }

    protected void displayLanguagesDialog(Language[] languages)
    {
        languageDialog = new ObjectSelectListDialog(getActivity(),languages, myTempUser.getFavoriteLanguages(), this);
        languageDialog.show(getActivity().getResources().getString(R.string.select_languages));
    }


    @Override
    public void onOk(ObjectSelectListDialog dialog, DialogItem[] items) {
        Object[] selectedObjects = dialog.getSelectedItems();
        if(dialog == languageDialog)
        {
            myTempUser.setFavoriteLanguages(Arrays.copyOf(selectedObjects,selectedObjects.length,Language[].class));
        }else if (dialog == subjectDialog)
        {
            myTempUser.setFavoriteSubjects(Arrays.copyOf(selectedObjects,selectedObjects.length,Subject[].class));
        }
    }

    @Override
    public void onCancel(ObjectSelectListDialog dialog, DialogItem[] items) {

    }

    @Override
    public void onRequestFinish(APIRequestBuilder requestBuilder, APIReturn apiReturn) {
        if(requestBuilder.getServiceName().equals(SubjectDAO.GET_ALL_SUBJECTS))
        {
            displaySubjectsDialog((Subject[]) apiReturn.getReturnObject());
        }

        if(requestBuilder.getServiceName().equals(LanguageDAO.GET_ALL_LANGUAGES))
        {
            displayLanguagesDialog((Language[]) apiReturn.getReturnObject());
        }
    }
}
