package com.example.jackie.mvptest.app;

import android.app.Application;

import com.example.jackie.mvptest.app.component.AppComponent;
import com.example.jackie.mvptest.app.component.DaggerAppComponent;
import com.example.jackie.mvptest.app.module.AppModule;

/**
 * Created by Jackie on 2018/7.
 */

public class AppBaseApplication extends Application {

    private static AppBaseApplication instance;

    public static AppBaseApplication getInstance() {
        return instance;
    }

    AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
    }
}
