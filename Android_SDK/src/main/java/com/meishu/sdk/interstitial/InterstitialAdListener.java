package com.meishu.sdk.interstitial;

public interface InterstitialAdListener {

    void onAdLoaded(InterstitialAd interstitialAd);

    void onAdExposure();

    void onAdClosed();

    void onAdError();
}
