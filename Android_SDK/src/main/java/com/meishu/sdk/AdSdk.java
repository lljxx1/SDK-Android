package com.meishu.sdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.meishu.sdk.config.CSJAdConfig;
import com.meishu.sdk.config.GDTAdConfig;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.SdkAdInfo;

public class AdSdk {
    /**
     * 是否开启测试模式
     * 测试模式会加更多调试日志，
     * 注意：测试模式不能用于生产环境
     */
    private static boolean testModeEnabled;

    public static void init( String appId, boolean testModeEnabled) throws RuntimeException {
        if (TextUtils.isEmpty(appId)) {
            throw new RuntimeException("appId初始值错误");
        }

        AdSdk.testModeEnabled = testModeEnabled;
        if (testModeEnabled) {//测试环境
//            MeishuConstants.ad_request_url = "http://123.59.48.113/sdk/req_ad";
            MeishuConstants.ad_request_url = "http://192.168.43.151:8080/req_ad";
//            MeishuConstants.ad_request_url = "http://192.168.43.151:8080/deep_link";
//            MeishuConstants.ad_request_url = "http://192.168.43.151:8080/req_ad_no_ad/1003908";
        } else {
            //TODO 添加生产环境地址
            MeishuConstants.ad_request_url = "http://123.59.48.113/sdk/req_ad";
        }
        MeishuAdConfig adConfig = MeishuAdConfig.getInstance();
        adConfig.setAppId(appId);

    }

    public static boolean isTestModeEnabled() {
        return testModeEnabled;
    }

    public static void InitSdkConfigIfNoInit(@NonNull Context context, @NonNull SdkAdInfo sdkAdInfo) {
        if (MeishuConstants.appIdKey_gdt.equalsIgnoreCase(sdkAdInfo.getSdk())) {//广点通
            GDTAdConfig gdtAdConfig = GDTAdConfig.getInstance();
            if (TextUtils.isEmpty(gdtAdConfig.getAppId())) {
                gdtAdConfig.setAppId(sdkAdInfo.getApp_id());
            }
        } else if (MeishuConstants.appIdKey_csj.equalsIgnoreCase(sdkAdInfo.getSdk())) {//穿山甲
            CSJAdConfig csjAdConfig = CSJAdConfig.getInstance();
            if (TextUtils.isEmpty(csjAdConfig.getAppId())) {
                csjAdConfig.buildConfig(context, sdkAdInfo.getApp_id());
            }
        } else {

        }
    }
}
