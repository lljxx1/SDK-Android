package com.meishu.sdk.meishu_ad.splash;

public interface AdListener {
    void onLoaded(NativeSplashAd splashAd);

    /**
     * 曝光回调
     */
    void onADExposure();

    void onADClosed();
}
