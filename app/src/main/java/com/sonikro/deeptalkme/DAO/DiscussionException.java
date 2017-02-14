package com.sonikro.deeptalkme.DAO;

import com.sonikro.deeptalkme.model.chat.ChatMessage;
import com.sonikro.deeptalkme.model.chat.MessageFactory;


/**
 * Created by Jonathan Nagayoshi on 10/07/2016.
 */
public class DiscussionException extends Exception {
    public DiscussionException(String message)
    {
        super(message);
    }

    public ChatMessage getErrorSystemMessage()
    {
        ChatMessage message = MessageFactory.factory(MessageFactory.SYSTEM, null);
        message.setText(getMessage());
        return message;
    }
}
