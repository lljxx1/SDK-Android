package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.splash.AdListener;
import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;

public class SplashAdListenerAdapter implements AdListener {
    private com.meishu.sdk.splash.SplashAdListener splashAdListener;

    public SplashAdListenerAdapter(@NonNull com.meishu.sdk.splash.SplashAdListener splashAdListener) {
        this.splashAdListener = splashAdListener;
    }

    @Override
    public void onLoaded(NativeSplashAd splashAd) {
        splashAdListener.onLoaded(new MeishuSplashAdAdapter(splashAd));
    }

    @Override
    public void onADExposure() {
        splashAdListener.onAdExposure();
    }

    @Override
    public void onADClosed() {
        splashAdListener.onAdClosed();
    }
}
