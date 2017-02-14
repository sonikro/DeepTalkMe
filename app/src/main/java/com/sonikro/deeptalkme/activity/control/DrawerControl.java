package com.sonikro.deeptalkme.activity.control;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;

import com.sonikro.deeptalkme.activity.layout.DrawerActivity;
import com.sonikro.deeptalkme.command.DrawerCommand;
import com.sonikro.deeptalkme.command.OpenCommand;
import com.sonikro.deeptalkme.framework.ActivityCommand;

/**
 * Created by Jonathan Nagayoshi on 29/05/2016.
 */
public abstract class DrawerControl extends ActivityControl implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerActivity myDrawerActivity;

    public DrawerControl(DrawerActivity activity)
    {
        super(activity);
        myDrawerActivity = activity;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {
            ActivityCommand command = DrawerCommand.factory(this, item.getItemId());
            if(command instanceof OpenCommand)
            {
                validateOpenCommand((OpenCommand)command);
            }
            command.dispatchCommand();
            myDrawerActivity.getDrawerLayout().closeDrawers();

        } catch (Exception ex)
        {
            myDrawerActivity.showSnackbar(ex.getMessage(), Snackbar.LENGTH_LONG);
        }
        return true;
    }

    protected void validateOpenCommand(OpenCommand command) throws Exception {
        if(getActivity().getClass().equals(command.getActivityClass()))
        {
            throw new Exception("Already at screen");
        }
    }
}
