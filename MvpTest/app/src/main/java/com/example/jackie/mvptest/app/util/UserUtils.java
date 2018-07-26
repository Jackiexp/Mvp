package com.example.jackie.mvptest.app.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Jackie on 2018/7/26.
 */

public class UserUtils {

    //关闭软键盘
    public static void closeKeyboard(Activity ac) {
        //关闭软键盘
        View view = ac.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private static Toast mToast;

    public static void initToast(Context context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static void showToast(String content) {
        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mToast.show();
        }
    }

    public static void showToast(int id) {
        mToast.setText(id);
        mToast.show();
    }

    public static Toast getToast() {
        return mToast;
    }

}
