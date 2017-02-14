package com.sonikro.deeptalkme.command;

import android.support.design.widget.Snackbar;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.api.DeepTalkMeAPI;
import com.sonikro.deeptalkme.framework.ActivityCommand;
import com.sonikro.deeptalkme.framework.DeepTalkMe;
import com.sonikro.deeptalkme.model.User;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class UpdateUserCommand extends ActivityCommand {
    private User user;
    private String new_password;
    public UpdateUserCommand(ActivityControl listener, User updateUser, String new_password)
    {
        super(listener);
        this.user = updateUser;
        this.new_password = new_password;
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        user.validate();
        user.updateUser(new_password);

    }

    @Override
    public void rollback(Exception exception) {
        System.out.println("Error updating user : " + exception.getMessage());
    }

    @Override
    public void onSuccess() {
        getListener().getActivity().getMyApplication().setUser(this.user);
        getListener().getActivity().showSnackbar("Data updated successfully", Snackbar.LENGTH_LONG);
        OpenHomeCommand homeCommand = new OpenHomeCommand(getListener());
        homeCommand.dispatchCommand();
    }

}
