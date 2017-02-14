package com.sonikro.deeptalkme.activity.control;

import android.view.View;

import com.sonikro.deeptalkme.activity.layout.HomeActivity;
import com.sonikro.deeptalkme.command.DiscussionSearchPartnerCommand;
import com.sonikro.deeptalkme.command.GetDiscussionCommand;
import com.sonikro.deeptalkme.command.LeaveDiscussionCommand;
import com.sonikro.deeptalkme.command.OpenSettingsCommand;
import com.sonikro.deeptalkme.command.QuietCommand;
import com.sonikro.deeptalkme.command.SequencerCommand;
import com.sonikro.deeptalkme.command.StartChatCommand;
import com.sonikro.deeptalkme.framework.CommandList;
import com.sonikro.deeptalkme.framework.dialog.DialogItem;
import com.sonikro.deeptalkme.framework.dialog.ObjectSelectListDialog;
import com.sonikro.deeptalkme.framework.dialog.ObjectSelectSingleDialog;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.DiscussionRequest;
import com.sonikro.deeptalkme.model.Language;
import com.sonikro.deeptalkme.model.Subject;
import com.sonikro.deeptalkme.model.User;

import java.util.Arrays;

/**
 * Created by Jonathan Nagayoshi on 28/05/2016.
 */
public class HomeControl extends DrawerControl implements View.OnClickListener,
                                            ObjectSelectListDialog.ObjectSelectListListener,
                                            ObjectSelectSingleDialog.ObjectSelectSingleListener{
    protected HomeActivity homeActivity;
    protected DiscussionRequest myDiscussionRequest;
    protected User myUser;
    public HomeControl(HomeActivity homeActivity)
    {
        super(homeActivity);
        this.homeActivity = homeActivity;
        myDiscussionRequest = new DiscussionRequest();
        myUser = getActivity().getMyApplication().getUser();
        myDiscussionRequest.setMy_user(myUser);
        endPossibleOpenDiscussion();
    }

    @Override
    public void onClick(View view) {
        if(view == homeActivity.btnSearchDiscussion)
        {
            searchForDiscussion();
        }

        if(view == homeActivity.btnSelectLanguage)
        {
            selectLanguage();
        }

        if(view == homeActivity.btnSelectSubjects)
        {
            selectSubjects();
        }
    }

    protected void searchForDiscussion()
    {
        CommandList commandList = new CommandList();

        DiscussionSearchPartnerCommand searchDiscussionCommand = new DiscussionSearchPartnerCommand(this, myDiscussionRequest);
        searchDiscussionCommand.setCommandMessage("Searching for Discussion");

        StartChatCommand startChatCommand = new StartChatCommand(this);
        startChatCommand.setCommandMessage("Starting Chat");

        commandList.add(searchDiscussionCommand);
        commandList.add(startChatCommand);

        SequencerCommand sequenceCommand = new SequencerCommand(this, commandList);
        sequenceCommand.dispatchWithProgressBar("Executing Command Sequence","Searching for a partner...",true);
    }

    protected void selectLanguage()
    {
        ObjectSelectSingleDialog dialog = new ObjectSelectSingleDialog(getActivity(),
                myUser.getFavoriteLanguages(), myDiscussionRequest.getLanguage(),this);
        dialog.show("Select language");
    }

    protected void selectSubjects()
    {
        ObjectSelectListDialog dialog = new ObjectSelectListDialog(getActivity(),
                myUser.getFavoriteSubjects(), myDiscussionRequest.getSubjects(),this);
        dialog.show("Select subjects");
    }

    @Override
    public void onOk(ObjectSelectListDialog dialog, DialogItem[] items) {
        Object[] selectedObjects = dialog.getSelectedItems();
        myDiscussionRequest.setSubjects(Arrays.copyOf(selectedObjects, selectedObjects.length, Subject[].class));
    }

    @Override
    public void onCancel(ObjectSelectListDialog dialog, DialogItem[] items) {
        //Do nothing
    }

    @Override
    public void onDialogItemSelection(ObjectSelectSingleDialog dialog, Object selectedObject) {
        myDiscussionRequest.setLanguage((Language) selectedObject);
    }

    public boolean isUserReady()
    {
        try {
            myUser.validate();
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }

    public void callUpdateUserActivity()
    {
        OpenSettingsCommand command = new OpenSettingsCommand(this);
        command.dispatchCommand();
    }

    public void endPossibleOpenDiscussion() {
        DiscussionRequest request = new DiscussionRequest();
        request.setMy_user(myUser);
        CommandList commandList = new CommandList();
        commandList.add(new GetDiscussionCommand(this, request));
        commandList.add(new LeaveDiscussionCommand(this));
        SequencerCommand sequencer = new SequencerCommand(this,commandList);
        QuietCommand silencer = new QuietCommand(this, sequencer);
        silencer.setNoProgressDialog(true);
        silencer.asyncExecute();
    }
}
