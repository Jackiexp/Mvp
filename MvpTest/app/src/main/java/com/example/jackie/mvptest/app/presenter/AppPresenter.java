package com.example.jackie.mvptest.app.presenter;


import com.example.jackie.mvptest.app.view.MvpView;

import javax.inject.Inject;

/**
 * Created by javalong on 16-12-27.
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
