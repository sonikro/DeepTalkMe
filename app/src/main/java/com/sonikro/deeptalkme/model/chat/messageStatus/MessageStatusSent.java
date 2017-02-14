package com.sonikro.deeptalkme.model.chat.messageStatus;


import com.sonikro.deeptalkme.R;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class MessageStatusSent extends MessageStatus {
    @Override
    public int getLayout() {
        return R.layout.my_sent_message;
    }
}
