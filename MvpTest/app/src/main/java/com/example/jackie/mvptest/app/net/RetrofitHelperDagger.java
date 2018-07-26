package com.example.jackie.mvptest.app.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jackie on 2018/6/13.
 */

public class RetrofitHelperDagger {

    private static final int DEFAULT_TIMEOUT = 30;

    private static Retrofit retrofit;
    private static OkHttpClient.Builder builder;
    private static RetrofitService retrofitService;

    /**
     * 启动后初始化
     */
    public static void init() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RetrofitService.BASE_URL).build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitService getRetrofitService() {
        return retrofitService;
    }

}
