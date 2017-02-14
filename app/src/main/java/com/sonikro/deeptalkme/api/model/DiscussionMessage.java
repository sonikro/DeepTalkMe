package com.sonikro.deeptalkme.api.model;

import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.chat.ChatMessage;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class DiscussionMessage {
    public Discussion discussion;
    public ChatMessage message;

    public DiscussionMessage(Discussion discussion, ChatMessage message)
    {
        this.discussion = discussion;
        this.message = message;
    }
}
