package com.sonikro.deeptalkme.model;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class Language implements Serializable {
    private String id_Language;
    private String text;

    public Language(String id_Language, String languageText)
    {
        setId_Language(id_Language);
        setText(languageText);
    }

    public String getId_Language() {
        return id_Language;
    }

    public void setId_Language(String id_Language) {
        this.id_Language = id_Language;
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
        if(object instanceof Language)
        {
            return ((Language) object).getId_Language().trim().equals(getId_Language().trim());
        }
        else
        {
            return false;
        }
    }
}
