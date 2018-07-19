package com.example.jackie.mvptest.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jackie.mvptest.entity.Meta;
import com.example.jackie.mvptest.entity.User;
import com.example.jackie.mvptest.net.RetrofitHelper;
import com.example.jackie.mvptest.views.LoginView;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by Jackie on 2018/6/8.
 */

public class LoginPresenterCompl implements LoginPresenter{

    private LoginView iLoginView;
    User user;

//    ILoginView iLoginView;
//    IUser user;
//    Handler    handler;

    public LoginPresenterCompl(LoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
//        handler = new Handler(Looper.getMainLooper());
    }

//    @Override
//    public void clear() {
//        iLoginView.onClearText();
//    }

    @Override
    public void login(String name, String password) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            iLoginView.onLoginResult("用户名密码不能为空");
            return;
        }

        Subscriber<Meta> subscriber = new Subscriber<Meta>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iLoginView.onLoginResult("登录失败");
                Log.e("tag MainActivity : ", "onError");
            }

            @Override
            public void onNext(Meta meta) {
                if ("200".equals(meta.getCode())) {
                    iLoginView.onLoginResult("登录成功");

                } else {
                    iLoginView.onLoginResult(meta.getMessage());
                }
                Log.e("tag MainActivity :", meta.getMessage());
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("pwd", password);
        map.put("device", "Device-Xp-TEST");
        String jsonString = JSON.toJSONString(map);

        RetrofitHelper.getInstance().login(subscriber, jsonString);
    }

//    @Override
//    public void setProgressBarVisiblity(int visiblity){
//        iLoginView.onSetProgressBarVisibility(visiblity);
//    }

    private void initUser(){
//        user = new UserModel("mvp","mvp");
    }
}
