package com.sonikro.deeptalkme.command;

import android.content.Intent;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.activity.layout.ChatActivity;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 5/07/2016.
 */
public class StartChatCommand extends ActivityCommand implements SequenceCommand {
    private Discussion myDiscussion;

    public StartChatCommand(ActivityControl listener)
    {
        super(listener);
    }

    public StartChatCommand(ActivityControl listener, Discussion discussion)
    {
        this(listener);
        setDiscussion(discussion);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        setupAppDiscussion();
    }


    protected void setupAppDiscussion()
    {
        getListener().getActivity().getMyApplication().setDiscussion(this.getDiscussion());
    }
    @Override
    public void rollback(Exception exception) {

    }

    @Override
    public void onSuccess() {
        System.out.println("StartChatCommand success");
        Intent intent = new Intent(getListener().getActivity(), ChatActivity.class);
        getListener().getActivity().startActivity(intent);
    }

    @Override
    public Object getResult() {
        return null;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Discussion)
        {
            setDiscussion((Discussion) object );
        }
    }

    public Discussion getDiscussion() {
        return myDiscussion;
    }

    public void setDiscussion(Discussion myDiscussion) {
        this.myDiscussion = myDiscussion;
    }
}
