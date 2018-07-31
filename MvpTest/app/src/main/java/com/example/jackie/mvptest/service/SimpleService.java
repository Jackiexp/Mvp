package com.example.jackie.mvptest.service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.example.jackie.mvptest.receiver.AlarmReceiver;
import com.example.jackie.mvptest.app.util.Utils;

import java.util.Random;

/**
 * Created by zejian
 * Time 2016/9/29.
 * Description:service simple demo
 */
public class SimpleService extends Service {

    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        System.out.println("onCreate invoke");
        startForeground(2222, new Notification());
        super.onCreate();
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand invoke");
        if (Utils.isFit()) {
            Utils.openDing();
//            Random random = new Random();
//            int i = random.nextInt(10);
//            if (i > 5) {
//            } else {
//                Utils.openWeixin();
//            }
        } else {
            Utils.openThis();
        }

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        int anHour = 60 * 60 * 1000; // 这是一小时的毫秒数
        int anHour = 7 * 60 * 1000;

        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;

        Intent i = new Intent(this, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, START_STICKY, startId);
    }


//    @SuppressLint("WrongConstant")
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        System.out.println("onStartCommand invoke");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1 * 60 * 1000);
//                        System.out.println("onStartCommand invoke -> run");
//                        if (Utils.isFit()) {
//                            Utils.openDing(getBaseContext());
//                        } else {
////                            Utils.openThis(getBaseContext());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        return super.onStartCommand(intent, START_STICKY, startId);
//    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        System.out.println("onDestroy invoke");
//        stopForeground(true);
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        sendBroadcast(intent);
        super.onDestroy();
    }
}