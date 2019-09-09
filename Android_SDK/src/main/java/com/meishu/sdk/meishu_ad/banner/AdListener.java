package com.meishu.sdk.meishu_ad.banner;

public interface AdListener {
    void onLoaded(BannerAd bannerAd);

    /**
     * 曝光回调
     */
    void onADExposure();

    void onADClosed();
}
