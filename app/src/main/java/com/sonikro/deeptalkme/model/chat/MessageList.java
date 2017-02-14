package com.sonikro.deeptalkme.model.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class MessageList implements Serializable{
    ArrayList<ChatMessage> mMessageList;

    public MessageList()
    {
        mMessageList = new ArrayList<ChatMessage>();
    }

    public void add(MessageList messages)
    {
        Iterator<ChatMessage> iterator = messages.getList().iterator();
        while(iterator.hasNext())
        {
            mMessageList.add(0,iterator.next());
        }
    }

    public void add(ChatMessage message)
    {
        mMessageList.add(0,message);
    }

    public void add(List<ChatMessage> messages)
    {
        mMessageList.addAll(0,messages);
    }

    public ArrayList<ChatMessage> getList()
    {
        return mMessageList;
    }

    public ChatMessage get(int index)
    {
        return mMessageList.get(index);
    }

    public int size()
    {
        return mMessageList.size();
    }
}
