package com.android.luggshare.common.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.luggshare.presentation.application.CustomApplication;

import static com.android.luggshare.common.keys.PreferenceKeys.COUNTRY_FIRST_TIME;
import static com.android.luggshare.common.keys.PreferenceKeys.IS_AUTHENTICATED;

public class ApplicationStateManager {
    private static final String SETTINGS_NAME = "application_state";
    private static ApplicationStateManager sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private ApplicationStateManager(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static ApplicationStateManager getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new ApplicationStateManager(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static ApplicationStateManager getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        else
        {
            return getInstance(CustomApplication.getContext());
        }
    }

    public boolean getIsAuthenticated() {
        return mPref.getBoolean(IS_AUTHENTICATED , false);
    }

    public void setIsAuthenticated(boolean value)
    {
        edit();
        mEditor.putBoolean(IS_AUTHENTICATED, value);
        commit();
    }

    public boolean getIsCountrySetFirstTime() {
        return mPref.getBoolean(COUNTRY_FIRST_TIME, false);
    }

    public void setIsCountrySetFirstTime(boolean value)
    {
        edit();
        mEditor.putBoolean(COUNTRY_FIRST_TIME, value);
        commit();
    }

    public void edit() {
        mEditor = mPref.edit();
    }

    public void commit() {
        mEditor.commit();
        mEditor = null;
    }

    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    private void doEdit() {
        if (mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
