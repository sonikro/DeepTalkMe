package com.sonikro.deeptalkme.model;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 */
public class Topic implements Serializable {
    private Integer id_topic;
    private Language language;
    private String text;

    public Integer getId_topic() {
        return id_topic;
    }

    public void setId_topic(Integer id_topic) {
        this.id_topic = id_topic;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return getText();
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Topic)
        {
            return getId_topic().equals(((Topic) object).getId_topic());
        }
        else
        {
            return false;
        }
    }
}
