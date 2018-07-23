package com.example.jackie.mvptest.views;

import com.example.jackie.mvptest.app.view.MvpView;

/**
 * Created by Jackie on 2018/6/8.
 */

public interface LoginView extends MvpView {

    void onLoginResult(String message);
}
