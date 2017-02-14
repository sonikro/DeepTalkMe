package com.sonikro.deeptalkme.model.chat;

import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.chat.messageStatus.MessageStatus;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 8/07/2016.
 */
public class ChatMessage implements Serializable {
    public static final String SENDER_ME = "SENDER_ME";
    public static final String SENDER_STRANGER = "SENDER_STRANGER";
    public static final String SENDER_SYSTEM = "SENDER_SYSTEM";

    private Integer id_message;
    private String text;
    private String sender;
    private String date_time;
    private MessageStatus status;

    public ChatMessage(ChatMessage chatMessage)
    {
        if(chatMessage != null)
        {
            setId_message(chatMessage.getId_message());
            setText(chatMessage.getText());
            setSender(chatMessage.getSender());
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getMessageLayout()
    {
        return 0; //This method should be implemented in the subclasses
    };

    public Integer getId_message() {
        return id_message;
    }

    public void setId_message(Integer id_message) {
        this.id_message = id_message;
    }

    @Override
    public String toString() {
        return getText();
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object object) {
        if(object instanceof ChatMessage)
        {
            return ((ChatMessage) object).getId_message().equals(getId_message());
        }
        return false;
    }
}
