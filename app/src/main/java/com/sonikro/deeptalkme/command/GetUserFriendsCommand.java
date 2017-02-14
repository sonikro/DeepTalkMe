package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.SequenceCommand;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class GetUserFriendsCommand extends ActivityCommand implements SequenceCommand{
    private User mUser;
    private User[] mFriends;
    public GetUserFriendsCommand(ActivityControl listener)
    {
        super(listener);
    }
    public GetUserFriendsCommand(ActivityControl listener, User user)
    {
        super(listener);
        mUser = user;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mFriends = mUser.getFriends();
    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error Fetching Friends");
        exception.printStackTrace(System.out);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public Object getResult() {
        return mFriends;
    }

    @Override
    public void setStarterObject(Object object) {
        if(object instanceof User)
        {
            mUser = (User) object;
        }
    }
}
