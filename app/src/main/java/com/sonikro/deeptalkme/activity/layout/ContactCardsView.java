package com.sonikro.deeptalkme.activity.layout;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sonikro.deeptalkme.adapters.ContactsAdapter;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class ContactCardsView {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;

    public ContactCardsView(RecyclerView recyclerView, Context context)
    {
        mRecyclerView = recyclerView;
        mContext = context;
    }

    public void display(User[] contacts)
    {
        mAdapter = new ContactsAdapter(contacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setReverseLayout(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
}
