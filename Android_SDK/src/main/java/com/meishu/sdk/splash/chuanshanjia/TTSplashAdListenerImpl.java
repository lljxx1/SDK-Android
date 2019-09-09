package com.meishu.sdk.splash.chuanshanjia;

import android.util.Log;

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
        Log.d(TAG, "onTimeout: ");
    }

    @Override
    public void onSplashAdLoad(TTSplashAd ttSplashAd) {
        if (this.meishuAdListener != null && ttSplashAd != null) {
            adNativeWrapper.getView().addView(ttSplashAd.getSplashView());
            this.meishuAdListener.onLoaded(new CSJSplashAd(this.adNativeWrapper.getSdkAdInfo(), ttSplashAd));
        }
    }
}
