package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.banner.AdListener;
import com.meishu.sdk.meishu_ad.banner.BannerAd;

public class BannerAdListenerAdapter implements AdListener {
    private com.meishu.sdk.banner.BannerAdListener bannerAdListener;

    public BannerAdListenerAdapter(@NonNull com.meishu.sdk.banner.BannerAdListener bannerAdListener) {
        this.bannerAdListener = bannerAdListener;
    }

    @Override
    public void onLoaded(BannerAd bannerAd) {
        bannerAdListener.onLoaded(new MeishuBannerAdAdapter(bannerAd));
    }

    @Override
    public void onADExposure() {
        bannerAdListener.onAdExposure();
    }

    @Override
    public void onADClosed() {
        bannerAdListener.onAdClosed();
    }
}
