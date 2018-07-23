package com.example.jackie.mvptest.app.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javalong on 16-12-27.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

//    @Provides
//    @Singleton
//    ServerApi provideServerApi(Context context){
//        RetrofitHelper.init(context);
//        return RetrofitHelper.getApi();
//    }

}
