package com.example.jackie.mvptest.activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jackie.mvptest.R;
import com.example.jackie.mvptest.app.AppBaseApplication;
import com.example.jackie.mvptest.app.util.Utils;
import com.example.jackie.mvptest.app.webview.WebActivity;
import com.example.jackie.mvptest.entity.MessageEvent;
import com.example.jackie.mvptest.presenter.LoginPresenter;
import com.example.jackie.mvptest.presenter.LoginPresenterCompl;
import com.example.jackie.mvptest.receiver.MyReceiver;
import com.example.jackie.mvptest.views.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements LoginView {

    public static final int CODE_TEST_ZEEO = 0;
    public static final int CODE_TEST_FIRST = 1;
    public static final int CODE_TEST_SECOND = 2;
    public static final int CODE_TEST_THIRD = 3;

    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.text_tip)
    TextView textTip;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;

    LoginPresenter loginPresenter;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterCompl(this);

        EventBus.getDefault().register(this);
        initReceiver();
        Utils.startServie(AppBaseApplication.getInstance());
    }

    public void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MY_BROADCAST");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);
    }

    @OnClick(R.id.bt_service)
    public void startService(){
        Utils.startServie(AppBaseApplication.getInstance());
    }

    @OnClick(R.id.bt_dagger)
    public void startDagger(){
        DaggerActivity.start(this);
    }

    @OnClick(R.id.bt_video)
    public void startVideo(){
        VideoTest.startActivity(this);
    }

    @OnClick(R.id.bt_event)
    public void startEvent(){
        EventBusTest1.startActivity(this);
    }

    @OnClick(R.id.bt_webview)
    public void startWebview(){
        WebActivity.startActivity(this);
    }

    @OnClick(R.id.bt_commit)
    public void login() {
        loginPresenter.login(editUsername.getText().toString(), editPassword.getText().toString());
    }

    @Override
    public void onLoginResult(String message) {
        textTip.setText(message);
        ToastUtils.showLong(message);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 1)
    public void onMessageEvent(MessageEvent event) {
        Log.e("", "onMessageEvent");
        switch (event.getCode()) {
            case CODE_TEST_ZEEO:
                Log.e("", "MainActivity = " + event.getMessage() + ", CODE = " + event.getCode());
                break;
            case CODE_TEST_FIRST:
                Log.e("", "MainActivity = " + event.getMessage() + ", CODE = " + event.getCode());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.e("", "onDestroy");
        EventBus.getDefault().unregister(this);
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
