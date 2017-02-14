package com.sonikro.deeptalkme.model.chat;

import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class MessageFactory {
    public static final String ME = "SENDER_ME";
    public static final String STRANGER = "SENDER_STRANGER";
    public static final String SYSTEM = "SENDER_SYSTEM";

    public static ChatMessage factory(String type, ChatMessage copyFrom) {
        ChatMessage message = null;
        switch (type) {
            case ME:
                message = new MyMessage(copyFrom);
                break;
            case STRANGER:
                message = new StrangerMessage(copyFrom);
                break;
            case SYSTEM:
                message = new SystemMessage(copyFrom);
                break;
        }
        if (message != null)
        {
            message.setSender(type);
        }
        return message;
    }
}
