package com.sonikro.deeptalkme.command;

import android.support.design.widget.Snackbar;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.Discussion;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class ShareUserDataCommand extends ActivityCommand implements SequenceCommand{
    private Discussion mDiscussion;

    public ShareUserDataCommand(ActivityControl listener, Discussion discussion)
    {
        super(listener);
        mDiscussion = discussion;
    }

    public ShareUserDataCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mDiscussion.shareData();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Sharing Data");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {
        getListener().getActivity().showSnackbar("Data successfully shared", Snackbar.LENGTH_LONG);
    }

    @Override
    public Object getResult() {
        return mDiscussion;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof Discussion)
        {
            mDiscussion = (Discussion) object;
        }
    }
}
