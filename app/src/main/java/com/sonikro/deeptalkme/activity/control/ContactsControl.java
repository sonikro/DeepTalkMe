package com.sonikro.deeptalkme.activity.control;

import android.view.View;

import com.sonikro.deeptalkme.activity.layout.ContactsActivity;
import com.sonikro.deeptalkme.command.GetUserFriendsCommand;
import com.sonikro.deeptalkme.command.SequencerCommand;
import com.sonikro.deeptalkme.command.SetupContactCardViewCommand;
import com.sonikro.deeptalkme.framework.CommandList;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class ContactsControl extends DrawerControl {
    private ContactsActivity mContactsActivity;

    public ContactsControl(ContactsActivity activity) {
        super(activity);
        mContactsActivity = activity;
    }

    @Override
    public void onClick(View v) {

    }

    public void setupContactsView()
    {
        CommandList commandList = new CommandList();
        User user = getActivity().getMyApplication().getUser();

        commandList.add(new GetUserFriendsCommand(this,user));
        commandList.add(new SetupContactCardViewCommand(this,mContactsActivity.mContactView));

        SequencerCommand sequencer = new SequencerCommand(this,commandList);
        sequencer.dispatchWithProgressBar("Setting up","Setting up Contact view",false);
    }
}
