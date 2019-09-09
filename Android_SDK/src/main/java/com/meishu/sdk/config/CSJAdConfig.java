package com.meishu.sdk.config;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.AdSdk;
import com.meishu.sdk.CSJConstants;
import com.meishu.sdk.service.RequestUtil;

public class CSJAdConfig {


    private TTAdConfig ttAdConfig;
    private String appId;

    private String appName = "";

    public void buildConfig(@NonNull Context context, String csjAppId) {
        this.appName = RequestUtil.getAppName(context);
        if (ttAdConfig == null) {
            appId = csjAppId;
            ttAdConfig = buildTTConfig(csjAppId);
            TTAdSdk.init(context, ttAdConfig);
        }
    }

    private TTAdConfig buildTTConfig(String csjAppId) {
        TTAdConfig.Builder builder = new TTAdConfig.Builder();
        builder.appId(csjAppId)
                .useTextureView(true) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
                .appName(this.appName)
                .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
                .allowShowNotify(true) //是否允许sdk展示通知栏提示
                .allowShowPageWhenScreenLock(true) //是否在锁屏场景支持展示广告落地页
                .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI, TTAdConstant.NETWORK_STATE_3G) //允许直接下载的网络状态集合
                .supportMultiProcess(false);//是否支持多进程
        //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
        if (AdSdk.isTestModeEnabled()) {
            builder.debug(true); //测试阶段打开，可以通过日志排查问题，上线时去除该调用
        }
        return builder.build();
    }


    private static final CSJAdConfig ourInstance = new CSJAdConfig();

    public static CSJAdConfig getInstance() {
        return ourInstance;
    }

    public String getAppId() {
        return appId;
    }

    private CSJAdConfig() {
    }

}
