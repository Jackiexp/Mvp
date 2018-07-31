package com.example.jackie.mvptest.app.module;

import android.content.Context;

import com.example.jackie.mvptest.app.net.RetrofitHelperDagger;
import com.example.jackie.mvptest.app.net.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jackie on 2018.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    RetrofitService provideRetrofitService() {
        RetrofitHelperDagger.init();
        return RetrofitHelperDagger.getRetrofitService();
    }

//    @Provides
//    @Singleton
//    Toast provideToast(Context context) {
//        UserUtils.initToast(context);
//        return UserUtils.getToast();
//    }

}
