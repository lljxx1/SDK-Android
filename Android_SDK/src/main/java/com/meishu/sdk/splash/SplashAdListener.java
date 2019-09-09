package com.meishu.sdk.splash;

public interface SplashAdListener {

    void onLoaded(SplashAd bannerAd);

    void onAdExposure();

    void onAdClosed();

    void onError();
}
