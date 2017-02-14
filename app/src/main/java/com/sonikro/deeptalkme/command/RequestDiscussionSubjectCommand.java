package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.framework.dialog.ObjectSelectSingleDialog;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.Subject;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 * Este comando pede para o usuário selecioanr um assunto da discusão
 */
public class RequestDiscussionSubjectCommand extends ActivityCommand implements SequenceCommand,
        ObjectSelectSingleDialog.ObjectSelectSingleListener{

    private Discussion mDiscussion;
    private ObjectSelectSingleDialog mSingleDialog;
    private Subject mSelectedSubject;

    public RequestDiscussionSubjectCommand(ActivityControl listener, Discussion discussion)
    {
        super(listener);
        mDiscussion = discussion;
    }

    public RequestDiscussionSubjectCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mSingleDialog = new ObjectSelectSingleDialog(getListener().getActivity(),
                mDiscussion.getSubjects(),null,this);
        mSingleDialog.show("Select a subject");

    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Selecting Subject from Discussion");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        System.out.println("Subject successfully selected");
    }

    @Override
    public Object getResult() {
        return mSelectedSubject;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Discussion)
        {
            mDiscussion = (Discussion) object;
        }
    }

    @Override
    public void onDialogItemSelection(ObjectSelectSingleDialog dialog, Object selectedObject) {
        if(selectedObject != null && selectedObject instanceof Subject)
        {
            mSelectedSubject = (Subject) selectedObject;
            callback(mSelectedSubject);
        }
    }
}
