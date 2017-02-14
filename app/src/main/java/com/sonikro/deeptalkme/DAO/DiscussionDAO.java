package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.api.APIReturn;
import com.sonikro.deeptalkme.api.model.DiscussionMessage;
import com.sonikro.deeptalkme.api.model.DiscussionTopic;
import com.sonikro.deeptalkme.api.model.ReportClaim;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.Topic;
import com.sonikro.deeptalkme.model.chat.ChatMessage;
import com.sonikro.deeptalkme.model.chat.MessageFactory;
import com.sonikro.deeptalkme.model.chat.MyMessage;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatus;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class DiscussionDAO extends APIDAO {
    public static final String SEND_MESSAGE = "SendMessage";
    public static final String READ_MESSAGES = "GetMessages";
    public static final String END_DISCUSSION = "EndDiscussion";
    public static final String STATUS_DISCUSSION_ENDED = "DISCUSSION_ENDED";
    public static final String SEND_RANDOM_QUESTION = "SendRandomQuestion";
    public static final String REPORT_USER = "ReportUser";
    public static final String SHARE_USER_DATA = "ShareData";

    private static DiscussionDAO instance = null;

    public static DiscussionDAO getInstance()
    {
        if(instance == null)
        {
            instance = new DiscussionDAO();
        }
        return instance;
    }

    public APIReturn sendMessage(Discussion discussion, ChatMessage message) throws Exception
    {
        APIReturn apiReturn = null;
        DiscussionMessage discussionMessage = new DiscussionMessage(discussion,message);
        try {
            getApi().connect(SEND_MESSAGE);
            getApi().writeToService(discussionMessage);
            apiReturn = getApi().readFromService(null);
            if(message instanceof MyMessage)
            {
                ((MyMessage) message).setStatus(MessageStatus.factory(MessageStatus.STATUS_SENT));
            }
            return apiReturn;
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public ChatMessage[] readMessages(Discussion discussion) throws Exception
    {
        APIReturn apiReturn;
        ChatMessage[] messages = null;
        try {
            getApi().connect(READ_MESSAGES);
            getApi().writeToService(discussion);
            apiReturn = getApi().readFromService(ChatMessage[].class);

            if(apiReturn.getReturnMessage().getStatus().equals(STATUS_DISCUSSION_ENDED))
            {
                throw new DiscussionException("This discusion is no longer active.");
            }
            factoryMessages((ChatMessage[]) apiReturn.getReturnObject());
            messages = (ChatMessage[]) apiReturn.getReturnObject();
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
        return messages;
    }

    protected void factoryMessages(ChatMessage[] messages)
    {
        ChatMessage tempMessage;
        for(int i = 0; i< messages.length; i++)
        {
            tempMessage = MessageFactory.factory(messages[i].getSender(),messages[i]);
            messages[i] = tempMessage;
        }
    }
    public void endDiscussion(Discussion discussion) throws Exception{
        APIReturn apiReturn = null;
        try {
            getApi().connect(END_DISCUSSION);
            getApi().writeToService(discussion);
            apiReturn = getApi().readFromService(null);

        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public void askRandomQuestion(Discussion discussion, Topic topic) throws Exception{
        DiscussionTopic discussionTopic = new DiscussionTopic();
        discussionTopic.discussion = discussion;
        discussionTopic.topic = topic;
        APIReturn apiReturn;
        try {
            getApi().connect(SEND_RANDOM_QUESTION);
            getApi().writeToService(discussionTopic);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public void reportUser(Discussion discussion, String claim) throws Exception
    {
        ReportClaim report = new ReportClaim();
        report.discussion = discussion;
        report.claim = claim;
        APIReturn apiReturn = new APIReturn();
        try {
            getApi().connect(REPORT_USER);
            getApi().writeToService(report);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }

    public void shareData(Discussion discussion) throws Exception {
        APIReturn apiReturn;
        try {
            getApi().connect(SHARE_USER_DATA);
            getApi().writeToService(discussion);
            apiReturn = getApi().readFromService(null);
        }catch(Exception ex)
        {
            throw ex;
        }finally {
            getApi().disconnect();
        }
    }
}
