package com.sonikro.deeptalkme.model;

import com.sonikro.deeptalkme.DAO.TopicDAO;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class Subject implements Serializable {
    private Integer id_subject;
    private Language language;
    private String text;

    public Subject(Language subjectLanguage)
    {
        setLanguage(subjectLanguage);
    }

    public Subject(Language subjectLanguage, String subjectText)
    {
        this(subjectLanguage);
        setText(subjectText);
    }

    public Subject(Integer id_subject, Language subjectLanguage, String subjectText)
    {
        this(subjectLanguage, subjectText);
        setId_subject(id_subject);
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


    public Integer getId_subject() {
        return id_subject;
    }

    public void setId_subject(Integer id_subject) {
        this.id_subject = id_subject;
    }

    @Override
    public String toString() {
        return getText();
    }


    @Override
    public boolean equals(Object object) {
        if(object instanceof  Subject)
        {
            return ((Subject) object).getId_subject().equals(getId_subject());
        }
        else
        {
            return false;
        }
    }

    public Topic[] getTopics() throws Exception
    {
        return TopicDAO.getInstance().getSubjectTopics(this);
    }
}
