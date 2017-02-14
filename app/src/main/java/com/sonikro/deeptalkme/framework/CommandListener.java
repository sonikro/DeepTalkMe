package com.sonikro.deeptalkme.framework;

/**
 * Created by Jonathan Nagayoshi on 12/05/2016.
 */
public interface CommandListener {
    public void onCommand(Command command);
    public void onCommandSuccess(Command command);
    public void onCommandError(Command command, Exception exception);
}