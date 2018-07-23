package com.example.jackie.mvptest.module;

import com.example.jackie.mvptest.views.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jackie on 2018/7/20.
 */

@Module
public class MainActivityModule {

    private LoginView loginView;

    public MainActivityModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginView provideLoginView() {
        return loginView;
    }
}
