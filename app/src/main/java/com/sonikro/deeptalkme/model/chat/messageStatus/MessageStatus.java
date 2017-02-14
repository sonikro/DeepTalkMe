package com.sonikro.deeptalkme.model.chat.messageStatus;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public abstract class MessageStatus {
    public static final String STATUS_NOT_SENT = "NOT_SENT";
    public static final String STATUS_SENT = "SENT";

    public static MessageStatus factory(String status)
    {
        switch(status)
        {
            case STATUS_NOT_SENT:
                return new MessageStatusNotSent();
            case STATUS_SENT:
                return new MessageStatusSent();
        }
        return null;
    }

    public abstract int getLayout();
}
