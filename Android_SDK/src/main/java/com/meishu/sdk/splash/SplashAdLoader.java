package com.meishu.sdk.splash;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.meishu_ad.splash.SplashAdSlot;
import com.meishu.sdk.splash.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.splash.gdt.GDTSplashAdWrapper;
import com.meishu.sdk.splash.meishu.MeishuAdNativeWrapper;

/**
 * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
 * <p>
 * 在调用SDK之前，如果您的App的targetSDKVersion >= 23，
 * 那么一定要把"READ_PHONE_STATE"、"WRITE_EXTERNAL_STORAGE"、
 * "ACCESS_FINE_LOCATION"这几个权限申请到，否则SDK将不会工作。
 */
public class SplashAdLoader extends AdLoader {
    private static final String TAG = "SplashAdLoader";
    private Activity activity;
    private ViewGroup adContainer;
    private String posId;
    private SplashAdListener apiAdListener;
    private int fetchDelay;

    public SplashAdLoader(@NonNull Activity activity, @NonNull ViewGroup adContainer, @NonNull String posId, SplashAdListener apiAdListener, int fetchDelay) {
        super(activity, posId);
        this.activity = activity;
        this.adContainer = adContainer;
        this.posId = posId;
        this.apiAdListener = apiAdListener;
        this.fetchDelay = fetchDelay;
    }

    @Override
    protected DelegateChain createMeishuAdDelegate(Activity activity, MeishuAdInfo meishuAdInfo) {
        SplashAdSlot adSlot = new SplashAdSlot().new Builder()
                .setAppId(MeishuAdConfig.getInstance().getAppId())
                .setPosId(meishuAdInfo.getPid())
                .setImageUrls(meishuAdInfo.getSrcUrls())
                .setInteractionType(meishuAdInfo.getTarget_type())
                .setAdContainer(SplashAdLoader.this.adContainer)
                .setDUrl(meishuAdInfo.getdUrl())
                .setAppName(meishuAdInfo.getApp_name())
                .setDeepLink(meishuAdInfo.getDeep_link())
                .setMonitorUrl(meishuAdInfo.getMonitorUrl())
                .setClickUrl(meishuAdInfo.getClickUrl())
                .setDn_start(meishuAdInfo.getDn_start())
                .setDn_succ(meishuAdInfo.getDn_succ())
                .setDn_inst_start(meishuAdInfo.getDn_inst_start())
                .setDp_start(meishuAdInfo.getDp_start())
                .setDp_fail(meishuAdInfo.getDp_fail())
                .build();
        return new MeishuAdNativeWrapper(this, adSlot);
    }

    @Override
    protected DelegateChain createDelegate(SdkAdInfo sdkAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTSplashAdWrapper(this, sdkAdInfo);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(this, sdkAdInfo);
        } else {
            delegate = null;
        }
        return delegate;
    }

    @Override
    protected void handleNoAd() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onError();
        }
    }

    public SplashAdListener getApiAdListener() {
        return apiAdListener;
    }

    public ViewGroup getAdContainer() {
        return adContainer;
    }

    public int getFetchDelay() {
        return fetchDelay;
    }
}