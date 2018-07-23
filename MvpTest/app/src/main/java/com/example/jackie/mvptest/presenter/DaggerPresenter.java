package com.example.jackie.mvptest.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jackie.mvptest.app.presenter.AppPresenter;
import com.example.jackie.mvptest.entity.Meta;
import com.example.jackie.mvptest.net.RetrofitHelper;
import com.example.jackie.mvptest.views.LoginView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Jackie on 2018/7/23.
 */

public class DaggerPresenter extends AppPresenter<LoginView> {

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

        mvpView.onLoginResult("login");

//        Subscriber<Meta> subscriber = new Subscriber<Meta>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                mvpView.onLoginResult("登录失败");
//                Log.e("tag MainActivity : ", "onError");
//            }
//
//            @Override
//            public void onNext(Meta meta) {
//                if ("200".equals(meta.getCode())) {
//                    mvpView.onLoginResult("登录成功");
//
//                } else {
//                    mvpView.onLoginResult(meta.getMessage());
//                }
//                Log.e("tag MainActivity :", meta.getMessage());
//            }
//        };


//        Map<String, String> map = new HashMap<>();
//        map.put("name", name);
//        map.put("pwd", password);
//        map.put("device", "Device-Xp-TEST");
//        String jsonString = JSON.toJSONString(map);

//        RetrofitHelper.getInstance().login(subscriber, jsonString);
    }
}
