package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.DrawerControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 3/06/2016.
 */
public class DrawerCommand {
    public static ActivityCommand factory(DrawerControl myListener, int menu_id)
    {
        ActivityCommand command = null;
        switch (menu_id)
        {
            case R.id.menu_home:
                command = new OpenHomeCommand(myListener);
                break;
            case R.id.menu_contacts:
                command = new OpenContactsCommand(myListener);
                break;
            case R.id.menu_settings:
                command = new OpenSettingsCommand(myListener);
                break;
            case R.id.menu_logout:
                command = new LogoutCommand(myListener);
                break;
        }
        return command;
    }
}
