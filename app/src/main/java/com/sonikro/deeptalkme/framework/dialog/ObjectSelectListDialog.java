package com.sonikro.deeptalkme.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class ObjectSelectListDialog extends ObjectSelectDialog implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnClickListener{

    private Object[] initiallySelected;
    private ObjectSelectListListener myListener;

    public interface ObjectSelectListListener
    {
        public void onOk(ObjectSelectListDialog dialog, DialogItem[] items);
        public void onCancel(ObjectSelectListDialog dialog, DialogItem[] items);
    }


    public ObjectSelectListDialog(Context context, Object[] objectArray, Object[] initiallySelected, ObjectSelectListListener listener)
    {
        super(context,objectArray);

        myListener = listener;
        if(initiallySelected == null)
        {
            this.initiallySelected = new Object[0];
        }
        else
        {
            this.initiallySelected = initiallySelected;
        }
        setupInitiallySelected();
    }

    protected void setupInitiallySelected()
    {
        for(int i = 0; i < myDialogItems.length; i++)
        {
            for(int y = 0; y < this.initiallySelected.length; y++)
            {
                if(this.initiallySelected[y].equals(myDialogItems[i].getObject()))
                {
                    myDialogItems[i].setIsChecked(true);
                }
            }
        }
    }
    @Override
    public void show(String dialogTitle)
    {
        boolean[] selectedObjects = getInitiallySelectedObjects();
        CharSequence[] dialogItems = getItemCharSequenceArray();

        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle(dialogTitle);
        builder.setMultiChoiceItems(dialogItems, selectedObjects, this);
        builder.setPositiveButton("Ok", this);
        builder.setNegativeButton("Cancel", this);
        myDialog = builder.create();
        myDialog.show();
    }

    public Object[] getSelectedItems()
    {
        ArrayList<Object> objectList = new ArrayList();

        for(int i = 0; i < myDialogItems.length; i++)
        {
            if(myDialogItems[i].isChecked())
            {
                objectList.add(myDialogItems[i].getObject());
            }
        }

        Object[] objectArray = new Object[objectList.size()];
        objectList.toArray(objectArray);
        for(int i = 0; i < objectArray.length; i++)
        {
            System.out.println(objectArray[i]);
        }
        return objectArray;
    }

    protected boolean[] getInitiallySelectedObjects()
    {
        boolean[] selected = new boolean[myDialogItems.length];
        for(int i = 0; i < myDialogItems.length; i++)
        {
            selected[i] = false;
            for(int y = 0 ; y < initiallySelected.length; y++)
            {
                if(initiallySelected[y].equals(myDialogItems[i].getObject()))
                {
                    selected[i] = true;
                    break;
                }
            }
        }
        return selected;
    }

    @Override
    public void onClick(DialogInterface dialog, int selectedIndex, boolean isChecked) {
        myDialogItems[selectedIndex].setIsChecked(isChecked);
    }

    @Override
    public void onClick(DialogInterface dialog, int action) {
        switch(action)
        {
            case Dialog.BUTTON_POSITIVE:
                myListener.onOk(this, myDialogItems);
                break;
            case Dialog.BUTTON_NEGATIVE:
                myListener.onCancel(this, myDialogItems);
                break;
        }
    }
}
