package com.sonikro.deeptalkme.framework.dialog;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class DialogItem {
    private Object object;
    private boolean isChecked;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
