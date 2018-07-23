package com.example.jackie.mvptest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.jackie.mvptest.R;
import com.example.jackie.mvptest.app.AppBaseActivity;
import com.example.jackie.mvptest.component.DaggerMainActivityComponent;
import com.example.jackie.mvptest.module.MainActivityModule;
import com.example.jackie.mvptest.presenter.DaggerPresenter;
import com.example.jackie.mvptest.views.LoginView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jackie on 2018/7/23.
 */

public class DaggerActivity extends AppBaseActivity<DaggerPresenter> implements LoginView {

//    @BindView(R.id.bt_commit)
//    Button btCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_commit)
    public void btCommit() {
        mPresenter.login("", "");
    }

    @Override
    protected void initInjects() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onLoginResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, DaggerActivity.class);
        context.startActivity(starter);
    }
}
