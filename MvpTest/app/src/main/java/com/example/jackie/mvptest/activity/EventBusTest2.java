package com.example.jackie.mvptest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jackie.mvptest.R;
import com.example.jackie.mvptest.entity.MessageEvent;
import com.example.jackie.mvptest.presenter.LoginPresenter;
import com.example.jackie.mvptest.presenter.LoginPresenterCompl;
import com.example.jackie.mvptest.views.LoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTest2 extends Activity implements LoginView {

    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.text_tip)
    TextView textTip;
//    @BindView(R.id.edit_username)
//    EditText editUsername;
//    @BindView(R.id.edit_password)
//    EditText editPassword;
//
//    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus2);
        ButterKnife.bind(this);

//        loginPresenter = new LoginPresenterCompl(this);
    }

    @OnClick(R.id.bt_commit)
    public void login() {
        MessageEvent event = new MessageEvent();
        event.setCode(MainActivity.CODE_TEST_ZEEO);
        event.setMessage("I LL");
        EventBus.getDefault().post(event);
//        loginPresenter.login(editUsername.getText().toString(), editPassword.getText().toString());
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EventBusTest2.class);
        context.startActivity(intent);
    }

//    @Override
//    public void setPresenter(Object presenter) {
//
//    }

    @Override
    public void onLoginResult(String message) {
        textTip.setText(message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
