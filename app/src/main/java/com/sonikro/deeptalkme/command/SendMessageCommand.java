package com.sonikro.deeptalkme.command;

import android.app.ProgressDialog;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.chat.ChatMessage;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class SendMessageCommand extends ActivityCommand {
    private Discussion mDiscussion;
    private ChatMessage mMessage;
    public SendMessageCommand(ActivityControl listener, Discussion discussion, ChatMessage message)
    {
        super(listener);
        mDiscussion = discussion;
        mMessage = message;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mDiscussion.sendMessage(mMessage);
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Sending Message : ");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        System.out.println("Message successfully sent");
    }

    @Override
    protected ProgressDialog getProgressDialog() {
        return null; //No progress dialog
    }
}
