package com.example.jackie.mvptest.component;

import com.example.jackie.mvptest.activity.DaggerActivity;
import com.example.jackie.mvptest.app.annotation.ActivityScope;
import com.example.jackie.mvptest.app.component.AppComponent;
import com.example.jackie.mvptest.module.MainActivityModule;

import dagger.Component;

/**
 * Created by Jackie on 2018/7/20.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(DaggerActivity mainView);
}
