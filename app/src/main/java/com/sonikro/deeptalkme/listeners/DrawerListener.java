package com.sonikro.deeptalkme.listeners;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.layout.DrawerActivity;

/**
 * Created by Jonathan Nagayoshi on 29/05/2016.
 */
public class DrawerListener extends ActionBarDrawerToggle {

    public DrawerListener(DrawerActivity activity, DrawerLayout layout, Toolbar toolbar)
    {
        super(activity, layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }
    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        syncState();
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        syncState();
    }
}
