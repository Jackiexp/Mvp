package com.example.jackie.mvptest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jackie.mvptest.app.util.Utils;

/**
 * Created by Administrator on 2017/2/22.
 */

public class RingReceived extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.jackie.mvptest.RING".equals(intent.getAction())) {
            Log.i("test", "闹钟响了");
            Utils.openDing(context);
            //跳转到Activity
//            Intent intent1 = new Intent(context, RingActivity.class);
//            //广播跳转到activity.必须给intent设置标志位flags
//
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent1);

        }
    }
}

