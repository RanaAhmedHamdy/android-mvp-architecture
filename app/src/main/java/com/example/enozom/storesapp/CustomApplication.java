package com.example.enozom.storesapp;

import android.app.Application;
import android.content.Context;

public class CustomApplication extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return CustomApplication.context;
    }
}
