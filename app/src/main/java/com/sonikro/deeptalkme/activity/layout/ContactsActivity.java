package com.sonikro.deeptalkme.activity.layout;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.ContactsControl;

public class ContactsActivity extends DrawerActivity {
    public RecyclerView mRecyclerView;
    public ContactCardsView mContactView;
    private ContactsControl mContactControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initializeComponents();
        initializeController();
        setListeners();
        setupContactView();
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        setSnackbarView(findViewById(R.id.contacts_root));
        mRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);
        mContactView = new ContactCardsView(mRecyclerView,this);
    }

    @Override
    protected void initializeController() {
        super.initializeController();
        mContactControl = (ContactsControl)getController();
    }

    @Override
    protected void setListeners() {
        super.setListeners();
    }

    private void setupContactView()
    {
        mContactControl.setupContactsView();
    }
}
