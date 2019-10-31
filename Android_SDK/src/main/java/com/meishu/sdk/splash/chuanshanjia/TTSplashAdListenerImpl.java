package com.meishu.sdk.splash.chuanshanjia;

import android.view.View;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.meishu.sdk.splash.SplashAdListener;

public class TTSplashAdListenerImpl implements TTAdNative.SplashAdListener {
    private static final String TAG = "TTSplashAdListenerImpl";
    private SplashAdListener meishuAdListener;
    private CSJTTAdNativeWrapper adNativeWrapper;

    public TTSplashAdListenerImpl(CSJTTAdNativeWrapper adNativeWrapper, SplashAdListener meishuAdListener) {
        this.adNativeWrapper = adNativeWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onError(int i, String s) {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onError();
        }
    }

    @Override
    public void onTimeout() {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onError();
        }
    }

    @Override
    public void onSplashAdLoad(TTSplashAd ttSplashAd) {
        if (this.meishuAdListener != null && ttSplashAd != null) {
            CSJSplashAd csjSplashAd = new CSJSplashAd(this.adNativeWrapper.getSdkAdInfo(), ttSplashAd,this.meishuAdListener);
            View adView = ttSplashAd.getSplashView();

            //这里不添加TouchAdContainer，添加后会使广告不能全屏显示
//            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
//            touchContainer.setTouchPositionListener(new TouchPositionListener(csjSplashAd));
//            touchContainer.addView(adView);
//            adView = touchContainer;

            csjSplashAd.setAdView(adView);

            adNativeWrapper.getView().addView(adView);
            this.meishuAdListener.onLoaded(csjSplashAd);
        }
    }
}
