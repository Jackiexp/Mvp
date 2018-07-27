package com.example.jackie.mvptest.app.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jackie.mvptest.R;

/**
 * WebView
 * 介绍：https://www.jianshu.com/p/3fcf8ba18d7f
 * git地址：https://github.com/Wing-Li/Html5WebView/tree/master
 */
public class WebActivity extends Activity {

    private TextView mTxtHost;
    private EditText mEdtUrl;
    private Button mBtnSreach;

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initViews();
        initEvents();
    }

    private void initViews() {
        mTxtHost = (TextView) findViewById(R.id.host);
        mEdtUrl = (EditText) findViewById(R.id.edt_url);
        mBtnSreach = (Button) findViewById(R.id.btn_sreach);
    }

    private void initEvents() {
        // 搜索按钮 和 输入法右下角的“搜索” 点击事件是一致的
        mBtnSreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sreachUrl();
            }
        });
        mEdtUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    sreachUrl();
                }
                return false;
            }
        });

        // 点击前面 “https://” 切换 http
        mTxtHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = mTxtHost.getText().toString().trim();
                if (host.startsWith("https")) {
                    mTxtHost.setText("http://");
                } else {
                    mTxtHost.setText("https://");
                }
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        context.startActivity(intent);
    }

    private void sreachUrl() {
        String edt = mEdtUrl.getText().toString().trim();
        if (edt.startsWith("https") || edt.startsWith("http")) {
            mUrl = edt;
        } else {
            mUrl = mTxtHost.getText().toString() + edt;
        }

        Intent intent = new Intent(WebActivity.this, Html5Activity.class);
        if (!TextUtils.isEmpty(edt)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", mUrl);
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }
}
