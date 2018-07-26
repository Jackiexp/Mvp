package com.example.jackie.mvptest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jackie.mvptest.app.util.Utils;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.MY_BROADCAST")) {
            System.out.println("MyReceiver：onReceive -> startService");
            Utils.startServie(context);
        }
    }
}