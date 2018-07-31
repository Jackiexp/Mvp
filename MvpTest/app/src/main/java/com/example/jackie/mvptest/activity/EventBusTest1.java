package com.example.jackie.mvptest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.jackie.mvptest.R;
import com.example.jackie.mvptest.entity.MessageEvent;
import com.example.jackie.mvptest.presenter.LoginPresenter;
import com.example.jackie.mvptest.views.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTest1 extends Activity implements LoginView {

    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.text_tip)
    TextView textTip;
//    @BindView(R.id.edit_username)
//    EditText editUsername;
//    @BindView(R.id.edit_password)
//    EditText editPassword;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus1);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
//        loginPresenter = new LoginPresenterCompl(this);
    }

    @OnClick(R.id.bt_commit)
    public void login() {
        EventBusTest2.startActivity(this);
//        loginPresenter.login(editUsername.getText().toString(), editPassword.getText().toString());
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EventBusTest1.class);
        context.startActivity(intent);
    }

//    @Override
//    public void setPresenter(Object presenter) {
//
//    }

    @Override
    public void onLoginResult(String message) {
        textTip.setText(message);
        ToastUtils.showLong(message);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 5)
    public void onMessageEvent1(MessageEvent event) {
        Log.i("1i EventBusTest1 ", "EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
        Log.d("2d EventBusTest1 ", "EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
        Log.v("3v EventBusTest1 ", "EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
        Log.e("4e EventBusTest1 ", "EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
        Log.wtf("5wtf EventBusTest1 ", "EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
        switch (event.getCode()) {
            case MainActivity.CODE_TEST_ZEEO:
                Log.e("", "6e EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
                break;
            case MainActivity.CODE_TEST_FIRST:
                Log.e("", "7e EventBusTest1 = " + event.getMessage() + ", CODE = " + event.getCode());
                break;
        }
//        EventBus.getDefault().cancelEventDelivery(event);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
