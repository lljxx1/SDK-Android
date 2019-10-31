package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
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
        View adView =ttBannerAd.getBannerView();
        TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(bannerAd));
        touchContainer.addView(adView);
        adView=touchContainer;
        bannerAd.setAdView(adView);

        bannerADListener.onLoaded(bannerAd);
    }
}
