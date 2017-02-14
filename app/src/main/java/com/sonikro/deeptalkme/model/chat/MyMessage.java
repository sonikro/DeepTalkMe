package com.sonikro.deeptalkme.model.chat;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatus;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatusNotSent;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatusSent;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class MyMessage extends ChatMessage {

    public MyMessage(ChatMessage chatMessage) {
        super(chatMessage);
    }

    @Override
    public int getMessageLayout() {
        if(getStatus() != null)
        {
            return getStatus().getLayout();
        }

        return R.layout.my_sent_message;
    }

}
