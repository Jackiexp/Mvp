package com.example.jackie.mvptest.app.config;

import okhttp3.MediaType;

/**
 * Created by Jackie on 2018/6/26.
 */

public class Constants {
    /**
     * 腾讯 视频
     * 票据过期(需更新票据userSig)
     */
    public static final int ERR_EXPIRE = 8051;
    public static final int SDKAPPID = 1400104540;
    public static final int ACCOUNTTYPE = 29994;

    public static final MediaType TEXT_PLAIN_TYPE = MediaType.parse("text/plain;charset=utf-8");

}
