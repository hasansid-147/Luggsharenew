package com.android.luggshare.presentation.application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.android.luggshare.utils.LogUtils;


public class CustomApplication extends Application {

    private static Context mContext;
    public static String Current_Culture = "en-US";


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // init TLS for webCalls
        try {

            Bundle params = new Bundle();
            params.putString("LoginTime","time_stamp");

        } catch (Exception e) {
            LogUtils.LOGStackTrace(e);
        }
    }

    public static Context getContext(){
        return mContext;
    }
}