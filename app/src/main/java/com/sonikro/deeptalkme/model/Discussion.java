package com.sonikro.deeptalkme.model;

import android.os.Message;

import com.sonikro.deeptalkme.DAO.DiscussionDAO;
import com.sonikro.deeptalkme.model.chat.ChatMessage;
import com.sonikro.deeptalkme.model.chat.MessageFactory;
import com.sonikro.deeptalkme.model.chat.MessageList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class Discussion implements Serializable {
    protected Integer id_discussion;
    protected User my_user;
    protected Language language;
    protected Subject[] subjects;

    public void validate() throws Exception
    {
        //Check if the Discussion is valid to start searching for a partner
        if(getLanguage() == null)
        {
            throw new Exception("Discussion has no language defined.");
        }

        if(getSubjects() == null || (  ( getSubjects().length < 1) ) )
        {
            throw new Exception("Discussion needs at least one subject");
        }

        if(getMy_user() == null)
        {
            throw new Exception("Discussion has no source user");
        }
    }


    public Integer getId_discussion() {
        return id_discussion;
    }

    public void setId_discussion(Integer id_discussion) {
        this.id_discussion = id_discussion;
    }

    public User getMy_user() {
        return my_user;
    }

    public void setMy_user(User my_user) {
        this.my_user = my_user;

    }
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }


    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public void sendMessage(ChatMessage message) throws Exception
    {
        DiscussionDAO.getInstance().sendMessage(this, message);
    }

    public MessageList getNewMessagesList() throws Exception{
        ChatMessage[] messages = DiscussionDAO.getInstance().readMessages(this);
        List<ChatMessage> list = Arrays.asList(messages);
        MessageList messageList = new MessageList();
        messageList.add(list);
        return messageList;
    }

    public void askRandomQuestion(Topic topic) throws Exception
    {
        DiscussionDAO.getInstance().askRandomQuestion(this, topic);
    }

    public void end() throws Exception
    {
        DiscussionDAO.getInstance().endDiscussion(this);
    }

    public void reportUser(String claim) throws Exception
    {
        DiscussionDAO.getInstance().reportUser(this, claim);
    }

    public void shareData() throws Exception
    {
        DiscussionDAO.getInstance().shareData(this);
    }
}
