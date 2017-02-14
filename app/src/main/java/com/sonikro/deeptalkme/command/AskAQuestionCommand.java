package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.Command;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.Topic;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 */
public class AskAQuestionCommand extends ActivityCommand {
    private Discussion mDiscussion;
    private Subject mSubject;
    private Topic mTopic;
    public AskAQuestionCommand(ActivityControl listener,Discussion discussion)
    {
        super(listener);
        mDiscussion = discussion;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        RequestDiscussionSubjectCommand requestSubject = new RequestDiscussionSubjectCommand(getListener() , mDiscussion);
        requestSubject.setCallbackMethod(this, "handleSubjectSelection");
        requestSubject.execute();
    }

    public void handleSubjectSelection(Object object) throws Exception
    {
        mSubject = (Subject)object;
        RequestSubjectTopicCommand requestTopic = new RequestSubjectTopicCommand(getListener(),mSubject);
        requestTopic.setCallbackMethod(this,"handleTopicSelection");
        requestTopic.execute();
    }

    public void handleTopicSelection(Object object) throws Exception
    {
        mTopic = (Topic) object;
        //mDiscussion.askRandomQuestion(mTopic);
        DynamicCommand dynamicCommand = new DynamicCommand(getListener());
        dynamicCommand.setCaller(mDiscussion);



        dynamicCommand.setMethodName("askRandomQuestion");
        dynamicCommand.setParameterTypes(Topic.class);
        dynamicCommand.setParameters(mTopic);
        dynamicCommand.dispatchWithProgressBar("Executing","Asking for a question...",false);

    }

    @Override
    public void rollback(Exception exception) {

    }

    @Override
    public void onSuccess() {

    }
}
