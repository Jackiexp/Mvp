package com.example.jackie.mvptest.app.component;

import android.content.Context;
import android.widget.Toast;

import com.example.jackie.mvptest.app.module.AppModule;
import com.example.jackie.mvptest.app.net.RetrofitService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jackie on 2018/7/23.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

    /**
     * 这里必须加上这个,因为在LoginPresenter里面我@Inject ServerApi,
     * 这里不加的话,会造成这个ServerApi找不到
     */
    RetrofitService getRetrofitService();

    Toast getToast();
}
