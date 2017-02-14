package com.sonikro.deeptalkme.model.chat;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class StrangerMessage extends ChatMessage {

    public StrangerMessage(ChatMessage chatMessage) {
        super(chatMessage);
    }

    @Override
    public int getMessageLayout() {
        return R.layout.stranger_message;
    }
}
