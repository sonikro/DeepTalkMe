package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class LeaveDiscussionCommand extends ActivityCommand implements SequenceCommand{
    private Discussion mDiscussion;

    public LeaveDiscussionCommand(ActivityControl listener)
    {
        super(listener);
    }

    public LeaveDiscussionCommand(ActivityControl listener, Discussion discussion)
    {
        super(listener);
        mDiscussion = discussion;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mDiscussion.end();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Leaving Discussion :");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        System.out.println("Successfully left the discussion");
    }

    @Override
    public Object getResult() {
        return mDiscussion;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Discussion)
        {
            mDiscussion = (Discussion) object;
        }
    }
}
