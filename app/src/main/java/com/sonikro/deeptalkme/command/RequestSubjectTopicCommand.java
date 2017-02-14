package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.framework.dialog.ObjectSelectSingleDialog;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.Topic;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 * Este comando pede ao usuario, para selecionar um topico de um assunto
 */
public class RequestSubjectTopicCommand extends ActivityCommand implements SequenceCommand,
        ObjectSelectSingleDialog.ObjectSelectSingleListener{

    private Subject mSubject;
    private Topic mSelectedTopic;
    private ObjectSelectSingleDialog mSingleDialog;
    private Topic[] mTopics;
    public RequestSubjectTopicCommand(ActivityControl listener, Subject subject)
    {
        super(listener);
        mSubject = subject;
    }

    public RequestSubjectTopicCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        GetSubjectTopicsCommand getTopics = new GetSubjectTopicsCommand(getListener(),mSubject);
        getTopics.setCallbackMethod(this,"handleGetTopics");
        getTopics.asyncExecute();
    }

    public void handleGetTopics(Object object)
    {
        mTopics = (Topic[]) object;
        mSingleDialog = new ObjectSelectSingleDialog(getListener().getActivity(),
                mTopics,null,this);
        mSingleDialog.show("Select a topic");
    }
    @Override
    public void rollback(Exception exception) {
        System.out.println("Error selecting Topic");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        System.out.println("RequestSubjectTopic executed successfully");
    }

    @Override
    public Object getResult() {
        return mSelectedTopic;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Subject)
        {
            mSubject = (Subject)object;
        }
    }

    @Override
    public void onDialogItemSelection(ObjectSelectSingleDialog dialog, Object selectedObject) {
        if(selectedObject != null && selectedObject instanceof Topic)
        {
            mSelectedTopic = (Topic) selectedObject;
            callback(mSelectedTopic);
        }
    }
}
