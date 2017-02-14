package com.sonikro.deeptalkme.services;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Jonathan Nagayoshi on 9/08/2016.
 */
public class ServiceManager {
    private static ServiceManager mSingleton;
    private Context mServiceContext;
    public static ServiceManager getInstance(Context context)
    {
        if(mSingleton == null)
        {
            mSingleton = new ServiceManager(context);
        }
        return mSingleton;
    }

    public ServiceManager(Context context)
    {
        mServiceContext = context;
    }

    public boolean isServiceRunning(Class serviceClass)
    {
        ActivityManager manager = (ActivityManager) mServiceContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
