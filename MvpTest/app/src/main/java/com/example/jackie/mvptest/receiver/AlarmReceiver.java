package com.example.jackie.mvptest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.jackie.mvptest.app.util.Utils;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive -> startService");
        Utils.startServie(context);
    }

}
