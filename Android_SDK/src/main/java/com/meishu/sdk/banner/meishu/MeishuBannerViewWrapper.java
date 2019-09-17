package com.meishu.sdk.banner.meishu;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.banner.BannerAdSlot;
import com.meishu.sdk.banner.BannerAdListener;
import com.meishu.sdk.banner.BannerAdDelegate;


public class MeishuBannerViewWrapper implements BannerAdDelegate {

    private AdNative adNative;
    private BannerAdListener bannerAdListener;
    private BannerAdSlot adSlot;
    private Activity activity;


    public MeishuBannerViewWrapper(@NonNull Activity activity, @NonNull BannerAdSlot adSlot, @NonNull BannerAdListener bannerAdListener) {
        this.activity = activity;
        this.adNative = new AdNative(activity);
        this.adSlot = adSlot;
        this.bannerAdListener = bannerAdListener;
    }

    @Override
    public void loadAd() {
        this.adNative.loadBannerAd(this.adSlot, new BannerAdListenerAdapter(this, this.bannerAdListener));
    }

    @Override
    public void destroy() {
        //do nothing
    }

    public Activity getActivity() {
        return activity;
    }

    public BannerAdSlot getAdSlot() {
        return adSlot;
    }
}
