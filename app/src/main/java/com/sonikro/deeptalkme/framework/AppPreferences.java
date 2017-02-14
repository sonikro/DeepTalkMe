package com.sonikro.deeptalkme.framework;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jonathan Nagayoshi on 18/06/2016.
 */
public class AppPreferences {
    public static final String USER_TOKEN = "USER_TOKEN";
    private Activity myActivity;

    public AppPreferences(Activity activity)
    {
        setActivity(activity);
    }

    public void setActivity(Activity activity)
    {
        myActivity = activity;
    }

    public Activity getActivity()
    {
        return myActivity;
    }

    public void setPreference(String name, String value)
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("DEEPTALKME_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public String getPreference(String name, String defaultValue)
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("DEEPTALKME_PREF", Context.MODE_PRIVATE);
        String value = sharedPref.getString(name, defaultValue);
        return value;
    }

}
