package com.example.jackie.mvptest.presenter;

import android.util.Log;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.core.ILiveRoomOption;
import com.tencent.ilivesdk.view.AVRootView;

/**
 * Created by Jackie on 2018/6/27.
 */

public class RoomHelper implements ILiveRoomOption.onExceptionListener, ILiveRoomOption.onRoomDisconnectListener {
    private IRoomView roomView;
    private AVRootView avRootView;

    public RoomHelper(IRoomView view){
        roomView = view;
    }
    // 设置渲染控件
    public void setRootView(AVRootView avRootView){
        this.avRootView = avRootView;
        ILiveRoomManager.getInstance().initAvRootView(avRootView);
    }
    // 创建房间
    public int createRoom(int roomId){
        ILiveRoomOption option = new ILiveRoomOption()
                .imsupport(true)
                .exceptionListener(this)  // 监听异常事件处理
                .roomDisconnectListener(this)   // 监听房间中断事件
                .controlRole("user")    // 使用user角色
                .autoCamera(true)       // 进房间后自动打开摄像头并上行
                .autoMic(true);         // 进房间后自动要开Mic并上行

        return ILiveRoomManager.getInstance().createRoom(roomId, option, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                roomView.onEnterRoom();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                roomView.onEnterRoomFailed(module, errCode, errMsg);
            }
        });
    }
    // 退出房间
    public int quitRoom(){
        return ILiveRoomManager.getInstance().quitRoom(new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                roomView.onQuitRoomSuccess();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                roomView.onQuitRoomFailed(module, errCode, errMsg);
            }
        });
    }

    // 处理Activity事件
    public void onPause(){
        ILiveRoomManager.getInstance().onPause();
    }
    public void onResume(){
        ILiveRoomManager.getInstance().onResume();
    }

    @Override
    public void onException(int exceptionId, int errCode, String errMsg) {
        //处理异常事件
    }

    @Override
    public void onRoomDisconnect(int errCode, String errMsg) {
        // 处理房间中断(一般为断网或长时间无长行后台回收房间)
    }


//    private void resetRenderLayout(){
//        //在此判断视频数量
//        int num = identifiers.size();
//        Log.i("identifierChange","reset num:"+num);
//        //closeUserView方法完成了删除并向前顺移，因此不需要再对视频index进行修改。
////        for (int i=0;i<ILiveConstants.MAX_AV_VIDEO_NUM;i++){
////            if (i<num){
////                avRootView.bindIdAndView(i,CommonConstants.Const_VideoType_Camera,identifiers.get(i));
////            }
////            else {
////                avRootView.bindIdAndView(i,CommonConstants.Const_VideoType_Camera,null);
////            }
////        }
//        if (num==1){
////            trViewSingle();
//        }else if (num==2){
//            trViewDouble();
////        }else if (num<5){
////            trViewQuarter();
////        }else {
////            trViewNonuple();
//        }
//    }

    public void trViewDouble(){
        avRootView.getViewByIndex(0).setPosLeft(0);
        avRootView.getViewByIndex(0).setPosTop(0);
        avRootView.getViewByIndex(0).setPosWidth(avRootView.getWidth());
        avRootView.getViewByIndex(0).setPosHeight(avRootView.getHeight()/2);
        avRootView.getViewByIndex(0).autoLayout();

        avRootView.getViewByIndex(1).setPosLeft(0);
        avRootView.getViewByIndex(1).setPosTop(avRootView.getHeight()/2);
        avRootView.getViewByIndex(1).setPosWidth(avRootView.getWidth());
        avRootView.getViewByIndex(1).setPosHeight(avRootView.getHeight()/2);
        avRootView.getViewByIndex(1).autoLayout();
    }

}