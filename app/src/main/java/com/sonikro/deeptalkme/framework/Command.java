package com.sonikro.deeptalkme.framework;

/**
 * Created by Jonathan Nagayoshi on 12/05/2016.
 */
public interface Command {

    public void execute() throws Exception;

    public void rollback(Exception exception);

    public void onSuccess();

    public boolean wasExecuted();

}
