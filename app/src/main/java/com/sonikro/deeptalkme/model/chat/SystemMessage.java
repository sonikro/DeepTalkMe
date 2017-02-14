package com.sonikro.deeptalkme.model.chat;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class SystemMessage extends ChatMessage {

    public SystemMessage(ChatMessage chatMessage) {
        super(chatMessage);
    }

    @Override
    public int getMessageLayout() {
        return R.layout.system_message;
    }
}
