package com.example.jackie.mvptest.app.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.TimePicker;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jackie.mvptest.service.SimpleService;

import java.util.Calendar;

/**
 * Created by Jackie on 2018/7/12.
 */

public class Utils {

    public static void openDing() {
        String packageName = "com.alibaba.android.rimet";//要打开应用的包名,以钉钉为例
        if (AppUtils.isAppInstalled(packageName)) {
            AppUtils.launchApp(packageName);
        } else {
            ToastUtils.showLong("手机未安装该应用");
        }
    }

    public static void openWeixin() {
        String packageName = "com.tencent.wework";//要打开应用的包名,以企业微信为例
        if (AppUtils.isAppInstalled(packageName)) {
            AppUtils.launchApp(packageName);
        } else {
            ToastUtils.showLong("手机未安装该应用");
        }
    }

    public static void openThis() {
        String packageName = "com.example.jackie.mvptest";
        AppUtils.launchApp(packageName);
    }

    //设置闹钟(周期)
    public static void setAlarmCycle(final Context mContext) {
        //获取当前系统的时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        final PendingIntent[] pendingIntent = new PendingIntent[1];
        AlarmManager alarmManager;
        alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);


        //01.弹出时间对话框（选择时间）
        final AlarmManager finalAlarmManager = alarmManager;
        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //02.确定选择好的时间  i  i1
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, i);
                c.set(Calendar.MINUTE, i1);

                //04.时间一到，执行相对应的操作
                Intent intent = new Intent();
                intent.setAction("com.example.jackie.mvptest.RING");
                pendingIntent[0] = PendingIntent.getBroadcast(mContext, 0x102, intent, 0);

                //03.设置闹钟(周期)
                finalAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, pendingIntent[0]);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }


    public static boolean isFit() {
        return true;
//        Calendar cal = Calendar.getInstance();// 当前日期
//        int week = cal.get(Calendar.DAY_OF_WEEK);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
//        int minute = cal.get(Calendar.MINUTE);// 获取分钟
//        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
//        final int start = 8 * 60 + 55;// 起始时间 00:20的分钟数
//        final int end = 9 * 60 + 30;// 结束时间 8:00的分钟数
//        if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
//            System.out.println("周末 在外围外");
//            return false;
//        } else {
//            if (minuteOfDay >= start && minuteOfDay <= end) {
//                System.out.println("在外围内");
//                return true;
//            } else {
//                System.out.println("在外围外");
//                return false;
//            }
//        }
    }

    public static void startServie(Context context) {
        Intent intent = new Intent(context, SimpleService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
