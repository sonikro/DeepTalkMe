package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.DiscussionRequest;

import java.sql.SQLOutput;

/**
 * Created by Jonathan Nagayoshi on 10/07/2016.
 */
public class GetDiscussionCommand extends ActivityCommand implements SequenceCommand {
    private DiscussionRequest mRequest;
    private Discussion mDiscussion;
    public GetDiscussionCommand(ActivityControl listener, DiscussionRequest request)
    {
        super(listener);
        mRequest = request;

    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mDiscussion = mRequest.getDiscussion();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("No discussion found ");
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public Object getResult() {
        return mDiscussion;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object != null)
        {
            mRequest = (DiscussionRequest) object;
        }
    }
}
