package com.example.jackie.mvptest.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jackie.mvptest.app.component.AppComponent;
import com.example.jackie.mvptest.app.presenter.AppPresenter;

import javax.inject.Inject;

/**
 * Created by jackie on 2018.
 */

public abstract class AppBaseActivity<T extends AppPresenter> extends AppCompatActivity {

    @Inject
    protected T mPresenter;
//    @Inject
//    Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjects();
        mPresenter.attach();
    }

    protected abstract void initInjects();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    protected AppComponent getAppComponent() {
        return AppBaseApplication.getInstance().getAppComponent();
    }
}
