package com.meishu.sdk.interstitial;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.interstitial.gdt.GDTInterstitialAdWrapper;
import com.meishu.sdk.interstitial.meishu.MeishuAdNativeWrapper;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdSlot;

public class InterstitialAdLoader extends AdLoader {
    private static final String TAG = "InterstitialAdLoader";

    private Activity activity;
    private InterstitialAdListener apiAdListener;

    public InterstitialAdLoader(@NonNull Activity activity, @NonNull String posId, InterstitialAdListener adListener) {
        super(activity, posId);
        this.activity = activity;
        this.apiAdListener = adListener;
    }

    @Override
    protected DelegateChain createMeishuAdDelegate(Activity activity, MeishuAdInfo meishuAdInfo) {
        InterstitialAdSlot adSlot = new InterstitialAdSlot().new Builder()
                .setAppId(MeishuAdConfig.getInstance().getAppId())
                .setPosId(meishuAdInfo.getPid())
                .setImageUrls(meishuAdInfo.getSrcUrls())
                .setInteractionType(meishuAdInfo.getTarget_type())
                .setWidth(meishuAdInfo.getWidth())
                .setHeight(meishuAdInfo.getHeight())
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
            delegate = new GDTInterstitialAdWrapper(this, sdkAdInfo);
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
            this.apiAdListener.onAdError();
        }
    }

    public InterstitialAdListener getApiAdListener() {
        return apiAdListener;
    }
}
