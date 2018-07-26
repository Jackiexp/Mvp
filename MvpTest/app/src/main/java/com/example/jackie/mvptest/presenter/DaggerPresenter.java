package com.example.jackie.mvptest.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jackie.mvptest.app.presenter.AppPresenter;
import com.example.jackie.mvptest.entity.Meta;
import com.example.jackie.mvptest.app.net.RetrofitService;
import com.example.jackie.mvptest.views.LoginView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import com.example.jackie.mvptest.app.config.Constants;
/**
 * Created by Jackie on 2018/7/23.
 */

public class DaggerPresenter extends AppPresenter<LoginView> {

    @Inject
    RetrofitService retrofitService;

    @Inject
    public DaggerPresenter(LoginView mvpView) {
        super(mvpView);
    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {

    }

    public void login(String name, String password) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            mvpView.onLoginResult("用户名密码不能为空");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("pwd", password);
        map.put("device", "Device-Xp-TEST");
        String jsonString = JSON.toJSONString(map);

        RequestBody requestBody = RequestBody.create(Constants.TEXT_PLAIN_TYPE, jsonString);
        retrofitService.login(requestBody)
                .map(new Func1<Meta, Meta>() {
                    @Override
                    public Meta call(Meta meta) {
                        return meta;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Meta>() {
                    @Override
                    public void onCompleted() {
//                        mvpView.onLoginResult("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.onLoginResult("登录失败");
                        Log.e("tag MainActivity : ", "onError");
                    }

                    @Override
                    public void onNext(Meta meta) {
                        if ("200".equals(meta.getCode())) {
                            mvpView.onLoginResult("登录成功");

                        } else {
                            mvpView.onLoginResult(meta.getMessage());
                        }
                        Log.e("tag MainActivity :", meta.getMessage());
                    }
                });
    }
}
