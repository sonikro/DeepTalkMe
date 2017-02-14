package com.sonikro.deeptalkme.activity.layout;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.chat.ChatMessage;
import com.sonikro.deeptalkme.adapters.ChatMessagesAdapter;
import com.sonikro.deeptalkme.model.chat.MessageList;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class DiscussionMessageView {
    private RecyclerView mRecyclerView;
    private ChatMessagesAdapter mAdapter;
    private MessageList mMessageList;
    private Discussion mDiscussion;

    public DiscussionMessageView(RecyclerView recyclerView, Discussion discussion, Context applicationContext)
    {
        setRecyclerView(recyclerView);
        setDiscussion(discussion);
        setupMessageList();
        setupAdapter();
        setupRecyclerView(applicationContext);
    }

    protected void setupMessageList()
    {
        mMessageList = new MessageList();
    }

    protected void setupAdapter()
    {
        mAdapter = new ChatMessagesAdapter(mMessageList);
    }

    protected void setupRecyclerView(Context applicationContext)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(applicationContext);
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void notifyDataChange()
    {
        mAdapter.notifyDataSetChanged();
    }


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public Discussion getDiscussion() {
        return mDiscussion;
    }

    public void setDiscussion(Discussion mDiscussion) {
        this.mDiscussion = mDiscussion;
    }

    public void add(ChatMessage message) {
        mMessageList.add(message);
        refresh();
    }

    public void add(MessageList messages)
    {
        mMessageList.add(messages);
        refresh();
    }

    public void refresh()
    {
        notifyDataChange();
    }
}
