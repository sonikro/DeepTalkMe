package com.sonikro.deeptalkme.command;

import android.app.ProgressDialog;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.DiscussionRequest;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class DiscussionSearchPartnerCommand extends ActivityCommand implements SequenceCommand {
    private static final Integer TIMEOUT = 60000; // 60 seconds
    private static final Integer INTERVAL_BETWEEN_CHECKS = 1000; // 5 seconds

    protected DiscussionRequest myDiscussionRequest;
    protected boolean requested;
    protected Discussion myDiscussion;

    public DiscussionSearchPartnerCommand(ActivityControl listener, DiscussionRequest request)
    {
        super(listener);
        this.myDiscussionRequest = request;
        requested = false;
        myDiscussion = null;
    }
    @Override
    public void execute() throws Exception {
        super.execute();
        this.myDiscussionRequest.validate();
        myDiscussionRequest.request();
        requested = true;
        startListening();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("RollBack DiscussionSearchPartner : "+exception.getMessage());
        exception.printStackTrace(System.out);
        if(requested)
        {
            try {
                myDiscussionRequest.cancelRequest();
            } catch (Exception ex)
            {
                System.out.println(ex);
            }
        }

    }

    @Override
    public void onSuccess() {
        System.out.println("DiscussionSearchPartner success");
    }

    protected void startListening() throws Exception
    {
        Integer numberOfAttempts = TIMEOUT / INTERVAL_BETWEEN_CHECKS;
        myDiscussion = null;
        for(int attempt = 0; attempt < numberOfAttempts; attempt++)
        {
            notifyProgress(numberOfAttempts, attempt);
            myDiscussion = myDiscussionRequest.getDiscussion();
            if(myDiscussion != null)
            {
                return;
            }
            Thread.sleep(INTERVAL_BETWEEN_CHECKS);
        }
        throw new Exception("Time out ! We couldn't find a partner");
    }

    @Override
    public Object getResult() {
        return myDiscussion;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof DiscussionRequest)
        {
            myDiscussionRequest = (DiscussionRequest) object;
        }
    }
}
