package com.example.jackie.mvptest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyMessengerService extends Service {

    public static final String TAG = "MyMessengerService";

    public static final int MSG_FROM_CLIENT_TO_SERVER = 1;
    public static final int MSG_FROM_SERVER_TO_CLIENT = 2;

    private Messenger mClientMessenger;
    private Messenger mServerMessenger = new Messenger(new ServerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        Log.w(TAG, "in onBind");
        return mServerMessenger.getBinder();
    }

    class ServerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.w(TAG, "thread name:" + Thread.currentThread().getName());
            switch (msg.what) {
            case MSG_FROM_CLIENT_TO_SERVER:
                Log.w(TAG, "receive msg from client");
                mClientMessenger = msg.replyTo;

                // service发送消息给client
                Message toClientMsg = Message.obtain(null, MSG_FROM_SERVER_TO_CLIENT);
                try {
                    Log.w(TAG, "server begin send msg to client");
                    mClientMessenger.send(toClientMsg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                super.handleMessage(msg);
            }
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w(TAG, "in onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.w(TAG, "in onDestroy");
        super.onDestroy();
    }
}