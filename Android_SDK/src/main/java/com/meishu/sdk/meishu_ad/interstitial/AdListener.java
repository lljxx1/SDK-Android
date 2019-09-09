package com.meishu.sdk.meishu_ad.interstitial;

public interface AdListener {
    void onLoaded(NativeInterstitialAd nativeAd);

    /**
     * 曝光回调
     */
    void onADExposure();

    void onADClosed();
}
