package com.example.jackie.mvptest.app.presenter;


import com.example.jackie.mvptest.app.view.MvpView;

import javax.inject.Inject;

/**
 * Created by Jackie on 2018/7.
 * presenter基类
 */
public abstract class AppPresenter<V extends MvpView>{
    @Inject
    protected V mvpView;

    public AppPresenter(V mvpView) {
        this.mvpView = mvpView;
    }

    public abstract void attach();
    public abstract void detach();
}
