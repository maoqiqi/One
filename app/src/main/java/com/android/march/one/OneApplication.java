package com.android.march.one;

import android.app.Application;

/**
 * Created by March on 2018/1/22.
 */
public class OneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        One.init(this);
    }
}