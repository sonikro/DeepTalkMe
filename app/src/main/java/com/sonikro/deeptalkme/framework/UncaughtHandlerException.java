package com.sonikro.deeptalkme.framework;

import android.os.*;
import android.os.Process;

/**
 * Created by Jonathan Nagayoshi on 13/08/2016.
 */
public class UncaughtHandlerException implements java.lang.Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
