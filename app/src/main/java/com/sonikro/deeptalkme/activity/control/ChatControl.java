package com.sonikro.deeptalkme.activity.control;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.layout.ChatActivity;
import com.sonikro.deeptalkme.command.AskAQuestionCommand;
import com.sonikro.deeptalkme.command.CommandServiceCommand;
import com.sonikro.deeptalkme.command.FinishActivityCommand;
import com.sonikro.deeptalkme.command.LeaveDiscussionCommand;
import com.sonikro.deeptalkme.command.OpenHomeCommand;
import com.sonikro.deeptalkme.command.ReportUserCommand;
import com.sonikro.deeptalkme.command.RequestDiscussionSubjectCommand;
import com.sonikro.deeptalkme.command.SendMessageCommand;
import com.sonikro.deeptalkme.command.SequencerCommand;
import com.sonikro.deeptalkme.command.ShareUserDataCommand;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.Command;
import com.sonikro.deeptalkme.framework.CommandList;
import com.sonikro.deeptalkme.framework.dialog.ConfirmDialog;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.chat.MessageFactory;
import com.sonikro.deeptalkme.model.chat.MyMessage;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatus;
import com.sonikro.deeptalkme.services.MessageReaderService;
import com.sonikro.deeptalkme.services.MessageReceiver;
import com.sonikro.deeptalkme.services.ServiceManager;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class ChatControl extends ActivityControl {
    public ChatActivity chatActivity;
    public Subject mSelectedSubject;
    public CommandServiceCommand mCommandService;
    public MessageReceiver mReceiver;
    private ServiceManager mServiceManager;
    protected Discussion mDiscussion;
    private static final int INTERVAL_BETWEEN_MESSAGES = 1000;

    public ChatControl(ChatActivity activity) {
        super(activity);
        chatActivity = activity;
        setupDiscussion();
        setupCommandService();
        setupServiceManager();
    }

    protected void setupDiscussion()
    {
        mDiscussion = chatActivity.getMyApplication().getDiscussion();
    }
    public void validateDiscussion()
    {
        if(getDiscussion() == null)
        {
            OpenHomeCommand command = new OpenHomeCommand(this);
            command.dispatchCommand();
            chatActivity.finish();
        }
    }

    protected void setupServiceManager() {
        mServiceManager = ServiceManager.getInstance(chatActivity);
    }

    protected void setupCommandService() {
        if(mCommandService != null)
        {
            mCommandService.cancelCommand();
            mCommandService = null;
        }
        mCommandService = new CommandServiceCommand(this);
        mCommandService.setIntervalBetweenCommands(INTERVAL_BETWEEN_MESSAGES);
        mCommandService.asyncExecute();
    }

    @Override
    public void onClick(View view) {
        if(view == chatActivity.btnSend) {
            sendMessage();
        }
    }

    public void validateServices()
    {
        if(!mServiceManager.isServiceRunning(MessageReaderService.class))
        {
            setupMessageReaderService();
        }
    }
    public void setupMessageReaderService()
    {
        if(mReceiver != null)
        {
            chatActivity.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        mReceiver = new MessageReceiver(chatActivity.mDiscussionMessageView);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MessageReaderService.ACTION_READ_MESSAGES);
        chatActivity.registerReceiver(mReceiver, intentFilter);
        startMessageReaderService();
        /*Intent serviceIntent = new Intent(chatActivity, MessageReaderService.class);
        Bundle data = new Bundle();
        data.putSerializable(MessageReaderService.SERIALIZED_DISCUSSION,chatActivity.getChatDiscussion());
        serviceIntent.putExtras(data);
        chatActivity.startService(serviceIntent);*/
    }

    protected void sendMessage()
    {
        if(chatActivity.validateInput())
        {
            MyMessage message = getMessageFromActivity();

            addMessageToDisplay(message);

            SendMessageCommand command = buildSendMessageCommand(message);

            addCommandToService(command);

            clearMessageField();
        }
    }

    protected void addCommandToService(SendMessageCommand command) {
        mCommandService.addCommand(command);
    }

    protected void clearMessageField() {
        chatActivity.txtMessage.setText("");
    }



    protected SendMessageCommand buildSendMessageCommand(MyMessage message) {
        return new SendMessageCommand(this,getDiscussion(),message);
    }

    protected void addMessageToDisplay(MyMessage message) {
        chatActivity.addMessageToList(message);
    }

    protected MyMessage getMessageFromActivity()
    {
        MyMessage message = (MyMessage) MessageFactory.factory(MessageFactory.ME, null);
        message.setText(chatActivity.txtMessage.getText().toString());
        message.setStatus(MessageStatus.factory(MessageStatus.STATUS_NOT_SENT));
        return message;
    }

    public void destroyServices()
    {
        chatActivity.unregisterReceiver(mReceiver);
        mCommandService.cancelCommand();
        mReceiver = null;
    }
    public void startMessageReaderService()
    {
        Intent serviceIntent = new Intent(getActivity(), MessageReaderService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MessageReaderService.SERIALIZED_DISCUSSION, getDiscussion());
        serviceIntent.putExtras(bundle);
        getActivity().startService(serviceIntent);
    }

    public boolean handleOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.chat_menu_ask:
                askSubjectQuestion();
                break;
            case R.id.chat_menu_report:
                reportUser();
                break;
            case R.id.chat_menu_leave:
                goHome(true);
                break;
            case R.id.chat_menu_share:
                shareData();
                break;
        }
        return true;
    }

    protected void confirmExecution(ActivityCommand command, int message, String executionMethod)
    {
        ConfirmDialog confirmDialog = new ConfirmDialog(getActivity(),executionMethod);
        confirmDialog.setYesCommand(command);
        confirmDialog.setNoCommand(null);
        confirmDialog.display(message);
    }
    protected void shareData() {
        ShareUserDataCommand command = new ShareUserDataCommand(this,getDiscussion());
        confirmExecution(command, R.string.sure_share_data,ConfirmDialog.ASYNC_EXECUTE);
    }

    public void leaveDiscussion()
    {
        LeaveDiscussionCommand command =  new LeaveDiscussionCommand(this,getDiscussion());
        command.setNoProgressDialog(true);
        command.asyncExecute();
    }

    public  void goHome(boolean askConfirmation) {
        CommandList commandList = new CommandList();

        commandList.add(new FinishActivityCommand(this));
        SequencerCommand sequencer = new SequencerCommand(this,commandList);

        if(askConfirmation){
            confirmExecution(sequencer,R.string.sure_leave_discussion,ConfirmDialog.SAME_THREAD_EXECUTE);
        }else
        {
            sequencer.dispatchCommand();
        }

    }

    private void reportUser() {
        ReportUserCommand command = new ReportUserCommand(this,getDiscussion());
        command.dispatchCommand();
    }

    protected void askSubjectQuestion() {
        AskAQuestionCommand command = new AskAQuestionCommand(this,getDiscussion());
        command.dispatchCommand();
    }

    @Override
    public void onCommandSuccess(Command command) {
        super.onCommandSuccess(command);
        if(command instanceof RequestDiscussionSubjectCommand)
        {
            mSelectedSubject = (Subject)((RequestDiscussionSubjectCommand) command).getResult();
        }
        if(command instanceof LeaveDiscussionCommand)
        {
            OpenHomeCommand goHome = new OpenHomeCommand(this);
            goHome.dispatchCommand();
        }
    }

    public Discussion getDiscussion() {
        return mDiscussion;
    }
}
