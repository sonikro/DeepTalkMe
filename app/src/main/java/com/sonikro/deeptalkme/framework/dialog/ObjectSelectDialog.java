package com.sonikro.deeptalkme.framework.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public abstract class ObjectSelectDialog {
    protected DialogItem[] myDialogItems;
    protected AlertDialog myDialog;
    protected Context myContext;

    public abstract void show(String title);
    public ObjectSelectDialog(Context context, Object[] options)
    {
        myContext = context;
        convertToDialogItemArray(options);
    }

    protected void convertToDialogItemArray(Object [] objects)
    {
        myDialogItems = new DialogItem[objects.length];
        for(int i = 0; i < objects.length; i++)
        {
            myDialogItems[i] = new DialogItem();
            myDialogItems[i].setObject(objects[i]);
            myDialogItems[i].setIsChecked(false);
        }
    }

    protected CharSequence[] getItemCharSequenceArray()
    {
        CharSequence[] dialogItems = new CharSequence[myDialogItems.length];

        for(int i = 0; i < myDialogItems.length; i++)
        {
            dialogItems[i] = myDialogItems[i].getObject().toString();
        }
        return dialogItems;
    }

    protected void uncheckAllDialogItems()
    {
        for(int i = 0 ; i < myDialogItems.length; i++)
        {
            myDialogItems[i].setIsChecked(false);
        }
    }

}
