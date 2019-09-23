package com.meishu.sdk.banner;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.banner.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.banner.gdt.GDTViewWrapper;
import com.meishu.sdk.banner.meishu.MeishuViewWrapper;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.meishu_ad.banner.BannerAdSlot;

public class BannerAdLoader extends AdLoader {
    private static final String TAG = "BannerAdLoader";
    private BannerAdListener adListener;

    public BannerAdLoader(@NonNull Activity activity,@NonNull String posId, BannerAdListener adListener) {
        super(activity, posId);
        this.adListener = adListener;
    }

    @Override
    protected DelegateChain createMeishuAdDelegate(Activity activity, MeishuAdInfo meishuAdInfo) {
        BannerAdSlot adSlot = new BannerAdSlot().new Builder()
                .setAppId(MeishuAdConfig.getInstance().getAppId())
                .setPosId(meishuAdInfo.getPid())
                .setImageUrls(meishuAdInfo.getSrcUrls())
                .setInteractionType(meishuAdInfo.getTarget_type())
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
        return new MeishuViewWrapper(this, adSlot);
    }

    @Override
    protected DelegateChain createDelegate(SdkAdInfo sdkAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTViewWrapper(this, sdkAdInfo, adListener);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(this, sdkAdInfo);
        } else {
            delegate = null;
        }
        return delegate;
    }

    @Override
    protected void handleNoAd() {
        this.adListener.onError();
    }

    public BannerAdListener getAdListener() {
        return adListener;
    }
}
