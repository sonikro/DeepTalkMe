package com.sonikro.deeptalkme.activity.layout;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.DrawerControl;
import com.sonikro.deeptalkme.listeners.DrawerListener;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 29/05/2016.
 */
public abstract class DrawerActivity extends SuperActivity {
    protected NavigationView myNavigationView;
    protected DrawerLayout myDrawerLayout;
    protected DrawerListener myDrawerListener;
    protected ImageView mImageView;

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        setNavigationView(((NavigationView) findViewById(R.id.nav_view)));
        setDrawerLayout(((DrawerLayout) findViewById(R.id.drawer_layout)));

        setDrawerListener(new DrawerListener(this, getDrawerLayout(), getMyToolbar()));
        getDrawerLayout().addDrawerListener(getDrawerListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getDrawerListener().syncState();

        setupHeaderFields();
    }

    protected void setupHeaderFields()
    {
        User loggedUser = getMyApplication().getUser();
        View navHeader = myNavigationView.getHeaderView(0);
        if(navHeader != null && loggedUser != null)
        {
             TextView username = (TextView) navHeader.findViewById(R.id.header_username);
             TextView email = (TextView) navHeader.findViewById(R.id.header_email);
                mImageView = (ImageView) navHeader.findViewById(R.id.profile_image);
             username.setText(loggedUser.getName());
             email.setText(loggedUser.getEmail());
             mImageView.setImageDrawable(UserImageBuilder.buildUserDrawable(loggedUser));

        }

    }

    @Override
    protected void setListeners() {
        getNavigationView().setNavigationItemSelectedListener((DrawerControl)getController());
    }

    public NavigationView getNavigationView() {
        return myNavigationView;
    }

    public void setNavigationView(NavigationView myNavigationView) {
        this.myNavigationView = myNavigationView;
    }

    public DrawerLayout getDrawerLayout() {
        return myDrawerLayout;
    }

    public void setDrawerLayout(DrawerLayout myDrawerLayout) {
        this.myDrawerLayout = myDrawerLayout;
    }

    public DrawerListener getDrawerListener() {
        return myDrawerListener;
    }

    public void setDrawerListener(DrawerListener myDrawerListener) {
        this.myDrawerListener = myDrawerListener;
    }
}
