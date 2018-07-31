package com.example.jackie.mvptest.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jackie.mvptest.R;
import com.example.jackie.mvptest.app.config.Constants;
import com.example.jackie.mvptest.presenter.IRoomView;
import com.example.jackie.mvptest.presenter.RoomHelper;
import com.example.jackie.mvptest.presenter.VideoPresenter;
import com.example.jackie.mvptest.presenter.VideoPresenterCompl;
import com.example.jackie.mvptest.views.VideoView;
import com.tencent.TIMManager;
import com.tencent.av.sdk.AVContext;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveRoomConfig;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.core.ILiveRoomOption;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoTest extends Activity implements VideoView, IRoomView {

    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.bt_join)
    Button btJoin;
    @BindView(R.id.bt_creat)
    Button btCreat;
    @BindView(R.id.text_tip)
    TextView textTip;
    @BindView(R.id.text_version)
    TextView textVersion;
    @BindView(R.id.bt_quit)
    TextView btQuit;
    @BindView(R.id.av_root_view)
    AVRootView avRootView;
//    @BindView(R.id.edit_username)
//    EditText editUsername;
//    @BindView(R.id.edit_password)
//    EditText editPassword;

    VideoPresenter videoPresenter;
    RoomHelper roomHelper;

    private static final String sign = "eJxlj81Og0AURvc8BWGrkTuUoWLiokVK0VYi2sR0M6HDFG8bfjoMUmp8dxWbSOL6nHwn34em67rxsni*Sjgvm0Ix1VXC0G90A4zLP1hVmLJEsZFM-0FxrFAKlmyVkD0klFILYOhgKgqFWzwbrdhIxV13YNTpnvWZ3wkbgIBN7eFIjVkPl-7KC72WtItxnpMO48DJ59ckWHfNFB*4nZXcN3fryA9tEh-i*wn6k4MKeGjCKXrtrJ25bwCfZnL1ODeTGY9OaeJsouXF29Tx7rLbQVJhLs6fRgAucel4QN*FrLEsesECQon1Lf0c1z61L-WqXSc_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        checkPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        videoPresenter = new VideoPresenterCompl(this);

        // 判断仅在主线程进行初始化
        if (MsfSdkUtils.isMainProcess(this)) {
            // 初始化iLiveSDK
            ILiveSDK.getInstance().initSdk(this, Constants.SDKAPPID, Constants.ACCOUNTTYPE);
            // 初始化iLiveSDK房间管理模块
            ILiveRoomManager.getInstance().init(new ILiveRoomConfig());
        }

        roomHelper = new RoomHelper(this);
// 设置没有渲染时的背景色为蓝色(注意不支持在布局中直接设置)
        avRootView.getVideoGroup().setBackgroundColor(Color.BLUE);
// 设置渲染控件
        avRootView.setAutoOrientation(false);
        roomHelper.setRootView(avRootView);
        initView();
    }

    @OnClick(R.id.bt_commit)
    public void login() {
        videoPresenter.loginSDK("webrtc99", sign);
    }

    @OnClick(R.id.bt_join)
    public void join() {
        joinRoom(123);
    }

    @OnClick(R.id.bt_creat)
    public void creat() {
//        enableMic(true);
        roomHelper.createRoom(1234);
    }

    @OnClick(R.id.bt_quit)
    public void quit() {
        roomHelper.quitRoom();
    }

    @OnClick(R.id.bt_two)
    public void two() {
        trViewDouble();
    }

    @OnClick(R.id.bt_four)
    public void four() {
        trViewQuarter();
    }

    @OnClick(R.id.bt_change_front)
    public void changeFront() {
        enableCamera(ILiveConstants.FRONT_CAMERA, true);
    }

    @OnClick(R.id.bt_change_back)
    public void changeBack() {
        enableCamera(ILiveConstants.BACK_CAMERA, true);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, VideoTest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void initView() {
        textVersion.setText(" iLiveSDK: " + ILiveSDK.getInstance().getVersion() + "\n IMSDK:" +
                TIMManager.getInstance().getVersion() + "\n AVSDK:" +
                AVContext.sdkVersion);
    }

    @Override
    public void onLoginSDKSuccess() {
        ToastUtils.showLong("登录成功");
    }

    @Override
    public void onLoginSDKFailed(String module, int errCode, String errMsg) {
        ToastUtils.showLong("登录失败");
    }

    @Override
    public void onEnterRoom() {
        ToastUtils.showLong("加入成功 onEnterRoom");
        Log.e("VideoTest", "onEnterRoom");
    }

    @Override
    public void onEnterRoomFailed(String module, int errCode, String errMsg) {
        Log.e("VideoTest", "onEnterRoomFailed");
    }

    @Override
    public void onQuitRoomSuccess() {
        Log.e("VideoTest", "onQuitRoomSuccess");
    }

    @Override
    public void onQuitRoomFailed(String module, int errCode, String errMsg) {
        Log.e("VideoTest", "onQuitRoomFailed");
    }

    @Override
    public void onRoomDisconnect(String module, int errCode, String errMsg) {
        Log.e("VideoTest", "onRoomDisconnect");
    }

    // 加入房间
    public int joinRoom(int roomId) {
        ILiveRoomOption option = new ILiveRoomOption()
                .imsupport(false)       // 不需要IM功能
//                .exceptionListener(this)  // 监听异常事件处理
//                .roomDisconnectListener(this)   // 监听房间中断事件
                .controlRole("user")  // 使用user角色
                .autoCamera(false)       // 进房间后不需要打开摄像头
                .autoMic(false);         // 进房间后不需打开Mic

        return ILiveRoomManager.getInstance().joinRoom(roomId, option, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                ToastUtils.showLong("加入成功");
                Log.e("VideoTest", "onSuccess");
//                onEnterRoom();
//                avRootView.onEnterRoom();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Log.e("VideoTest", "onError");
//                avRootView.onEnterRoomFailed(module, errCode, errMsg);
            }
        });
    }

    // 摄像头
    public int enableCamera(int cameraId, boolean enable) {
        return ILiveRoomManager.getInstance().enableCamera(cameraId, enable);
    }
//
//    // 麦克风
//    public int enableMic(boolean enable) {
//        return ILiveRoomManager.getInstance().enableMic(enable);
//    }

    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this,
                        (String[]) permissions.toArray(new String[0]),
                        100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0) {
                    String needPermission = "";
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] < 0) {
                            needPermission += permissions[i] + " ";
                        }
                    }
                    if (!"".equals(needPermission)) {
                        ToastUtils.showLong("需要权限:");
                    }

                } else {
                    ToastUtils.showLong("没有获取权限");
                }
                break;
        }
    }

    public void trViewDouble() {
        avRootView.getViewByIndex(0).setPosLeft(0);
        avRootView.getViewByIndex(0).setPosTop(0);
        avRootView.getViewByIndex(0).setPosWidth(avRootView.getWidth() / 2);
        avRootView.getViewByIndex(0).setPosHeight(avRootView.getHeight());
        avRootView.getViewByIndex(0).autoLayout();

        avRootView.getViewByIndex(1).setPosLeft(avRootView.getWidth() / 2);
        avRootView.getViewByIndex(1).setPosTop(0);
        avRootView.getViewByIndex(1).setPosWidth(avRootView.getWidth() / 2);
        avRootView.getViewByIndex(1).setPosHeight(avRootView.getHeight());
        avRootView.getViewByIndex(1).autoLayout();
    }

    public void trViewQuarter() {
        // 计算视频画面的宽高
        int subWidth = avRootView.getWidth() / 2;
        int subHeight = avRootView.getHeight() / 2;

        // 设置视频画面左上角位置
        avRootView.getViewByIndex(0).setPosLeft(0);
        avRootView.getViewByIndex(0).setPosTop(0);

        avRootView.getViewByIndex(1).setPosLeft(subWidth);
        avRootView.getViewByIndex(1).setPosTop(0);

        avRootView.getViewByIndex(2).setPosLeft(0);
        avRootView.getViewByIndex(2).setPosTop(subHeight);

        avRootView.getViewByIndex(3).setPosLeft(subWidth);
        avRootView.getViewByIndex(3).setPosTop(subHeight);

        for (int i = 0; i < 4; i++) {
            avRootView.getViewByIndex(i).setPosWidth(subWidth);
            avRootView.getViewByIndex(i).setPosHeight(subHeight);
            avRootView.getViewByIndex(i).autoLayout();
        }
    }
}
