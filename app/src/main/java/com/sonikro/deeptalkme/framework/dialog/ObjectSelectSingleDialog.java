package com.sonikro.deeptalkme.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class ObjectSelectSingleDialog extends ObjectSelectDialog implements DialogInterface.OnClickListener{
    protected Object mySelectedItem;
    protected ObjectSelectSingleListener myListener;

    public ObjectSelectSingleDialog(Context context, Object[] options, Object selectedItem, ObjectSelectSingleListener listener)
    {
        super(context, options);
        mySelectedItem = selectedItem;
        this.myListener = listener;
    }

    public interface ObjectSelectSingleListener
    {
        public void onDialogItemSelection(ObjectSelectSingleDialog dialog, Object selectedObject);
    }

    @Override
    public void show(String title) {
        CharSequence[] dialogItems = getItemCharSequenceArray();

        int selectedItemIndex = getSelectedItemIndex();

        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle(title);
        builder.setSingleChoiceItems(dialogItems,selectedItemIndex,this);
        //builder.setPositiveButton("Ok", this);
        //builder.setNegativeButton("Cancel", this);
        myDialog = builder.create();
        myDialog.show();
    }

    protected int getSelectedItemIndex()
    {
        for(int i = 0; i < myDialogItems.length; i++)
        {
            if(myDialogItems[i].getObject().equals(mySelectedItem))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        uncheckAllDialogItems();
        myDialogItems[which].setIsChecked(true);
        myDialog.dismiss();
        myListener.onDialogItemSelection(this, myDialogItems[which].getObject());
    }
}
