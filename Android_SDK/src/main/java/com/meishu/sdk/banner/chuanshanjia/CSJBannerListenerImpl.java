package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.banner.BannerAdListener;

public class CSJBannerListenerImpl implements TTAdNative.BannerAdListener {
    private static final String TAG = "CSJBannerListenerImpl";
    private BannerAdListener bannerADListener;
    private CSJTTAdNativeWrapper adNativeWrapper;

    public CSJBannerListenerImpl(@NonNull CSJTTAdNativeWrapper adNativeWrapper, BannerAdListener bannerADListener) {
        this.adNativeWrapper = adNativeWrapper;
        this.bannerADListener = bannerADListener;
    }

    @Override
    public void onError(int i, String s) {
        if (this.bannerADListener != null) {
            this.bannerADListener.onError();
        }
    }

    @Override
    public void onBannerAdLoad(TTBannerAd ttBannerAd) {
        CSJBannerAd bannerAd = new CSJBannerAd(this.adNativeWrapper.getSdkAdInfo(), ttBannerAd);
        bannerADListener.onLoaded(bannerAd);
    }
}
