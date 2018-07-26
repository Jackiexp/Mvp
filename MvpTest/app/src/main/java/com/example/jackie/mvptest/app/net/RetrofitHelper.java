package com.example.jackie.mvptest.app.net;

import com.example.jackie.mvptest.entity.Meta;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jackie on 2018/6/13.
 */

public class RetrofitHelper {

    private static final int DEFAULT_TIMEOUT = 30;

    private Retrofit retrofit;
    private OkHttpClient.Builder builder;
    private RetrofitService retrofitService;

    public static final MediaType TEXT_PLAIN_TYPE = MediaType.parse("text/plain;charset=utf-8");

    /**
     * 获取RetrofitHelper对象的单例
     */
    private static class RetrofitManager {
        private static final RetrofitHelper retrofitManage = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return RetrofitManager.retrofitManage;
    }

    private RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        Map<String, String> map = new HashMap<String, String>();
////        map.put("token", "");
//        map.put("version", "3.2.3");
//        map.put("mobileType", "android");
//        builder.addInterceptor(new HeadersInterceptor(map));

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RetrofitService.BASE_URL).build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public void login(Subscriber<Meta> subscriber, String content) {

        RequestBody requestBody = RequestBody.create(TEXT_PLAIN_TYPE, content);
        retrofitService.login(requestBody)
                .map(new Func1<Meta, Meta>() {
                    @Override
                    public Meta call(Meta meta) {
                        return meta;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void update(Subscriber<Object> subscriber, Map<String, String> map) {
    }

    public void getMovies(Subscriber<Object> subscriber) {
        retrofitService.getMovies(0, 5)
                .map(new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    public void getMovies() {
//        Call<Object> call = retrofitService.getMovies(0, 10);
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                Log.e("sdfsd", "sfsdfs");
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                Log.e("sdfsd", "sfsdfs");
//            }
//        });
//        Log.e("sdfsd", "sfsdfs");
//    }

}
