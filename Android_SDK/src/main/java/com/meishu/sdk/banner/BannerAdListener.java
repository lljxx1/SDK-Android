package com.meishu.sdk.banner;

public interface BannerAdListener {

    void onLoaded(BannerAd bannerAd);
    /**
     * 曝光回调
     */
    void onAdExposure();

    void onAdClosed();

    /**
     * 加载广告出错
     */
    void onError();
}
