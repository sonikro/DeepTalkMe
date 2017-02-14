package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.DrawerControl;
import com.sonikro.deeptalkme.activity.layout.ContactsActivity;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class OpenContactsCommand extends OpenCommand{
    public OpenContactsCommand(DrawerControl myListener) {
        super(myListener);
        setActivityClass(ContactsActivity.class);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        getListener().getActivity().finish();
    }
}
