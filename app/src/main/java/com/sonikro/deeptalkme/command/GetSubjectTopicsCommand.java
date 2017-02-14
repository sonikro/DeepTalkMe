package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.Topic;

/**
 * Created by Jonathan Nagayoshi on 14/07/2016.
 */
public class GetSubjectTopicsCommand extends ActivityCommand {
    private Subject mSubject;
    private Topic[] mTopics;
    public GetSubjectTopicsCommand(ActivityControl listener, Subject subject)
    {
        super(listener);
        mSubject = subject;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mTopics = mSubject.getTopics();
    }

    @Override
    public void rollback(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        callback(mTopics);
    }
}
