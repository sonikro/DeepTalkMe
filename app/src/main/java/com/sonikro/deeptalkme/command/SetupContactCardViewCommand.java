package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.activity.layout.ContactCardsView;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class SetupContactCardViewCommand extends ActivityCommand implements SequenceCommand {
    private ContactCardsView mContactView;
    private User[] mContacts;

    public SetupContactCardViewCommand(ActivityControl listener,ContactCardsView view, User[] contacts)
    {
        super(listener);
        mContactView = view;
        mContacts = contacts;
    }

    public SetupContactCardViewCommand(ActivityControl listener,ContactCardsView view)
    {
        super(listener);
        mContactView = view;
    }


    @Override
    public void rollback(Exception exception) {
        System.out.println("Error generating Contact View Card");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        mContactView.display(mContacts); //Called on main thread
    }

    @Override
    public Object getResult() {
        return mContactView;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof User[])
        {
            mContacts = (User[]) object;
        }
    }
}
