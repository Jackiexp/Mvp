package com.example.jackie.mvptest.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jackie.mvptest.entity.Meta;
import com.example.jackie.mvptest.entity.User;
import com.example.jackie.mvptest.net.RetrofitHelper;
import com.example.jackie.mvptest.views.LoginView;
import com.example.jackie.mvptest.views.VideoView;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by Jackie on 2018/6/8.
 */

public class VideoPresenterCompl implements VideoPresenter{

//    private VideoView videoView;
//    User user;

//    ILoginView iLoginView;
//    IUser user;
//    Handler    handler;

//    public VideoPresenterCompl(VideoView videoView) {
//        this.videoView = videoView;
////        initUser();
////        handler = new Handler(Looper.getMainLooper());
//    }


    private VideoView videoView;

    public VideoPresenterCompl(VideoView view){
        videoView = view;
    }

    @Override
    public void loginSDK(String userId, String userSig){
        ILiveLoginManager.getInstance().iLiveLogin(userId, userSig, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                videoView.onLoginSDKSuccess();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                videoView.onLoginSDKFailed(module, errCode, errMsg);
            }
        });
    }

//    @Override
//    public void clear() {
//        iLoginView.onClearText();
//    }

//    @Override
//    public void login(String name, String password) {
//
//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
//            iLoginView.onLoginResult("用户名密码不能为空");
//            return;
//        }
//
//        Subscriber<Meta> subscriber = new Subscriber<Meta>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                iLoginView.onLoginResult("登录失败");
//                Log.e("tag MainActivity : ", "onError");
//            }
//
//            @Override
//            public void onNext(Meta meta) {
//                if ("200".equals(meta.getCode())) {
//                    iLoginView.onLoginResult("登录成功");
//
//                } else {
//                    iLoginView.onLoginResult(meta.getMessage());
//                }
//                Log.e("tag MainActivity :", meta.getMessage());
//            }
//        };
//
//
//        Map<String, String> map = new HashMap<>();
//        map.put("name", name);
//        map.put("pwd", password);
//        map.put("device", "Device-Xp-TEST");
//        String jsonString = JSON.toJSONString(map);
//
//        RetrofitHelper.getInstance().login(subscriber, jsonString);
//    }

//    @Override
//    public void setProgressBarVisiblity(int visiblity){
//        iLoginView.onSetProgressBarVisibility(visiblity);
//    }

//    private void initUser(){
////        user = new UserModel("mvp","mvp");
//    }
}
