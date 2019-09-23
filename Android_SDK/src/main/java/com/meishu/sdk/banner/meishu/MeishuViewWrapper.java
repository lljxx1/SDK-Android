package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.banner.BannerAdLoader;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.banner.BannerAdSlot;


public class MeishuViewWrapper extends BaseMeishuWrapper {

    private AdNative adNative;
    private BannerAdSlot adSlot;
    private BannerAdLoader adLoader;


    public MeishuViewWrapper(@NonNull BannerAdLoader adLoader, @NonNull BannerAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader = adLoader;
        this.adNative = new AdNative(adLoader.getActivity());
        this.adSlot = adSlot;
    }

    @Override
    public void loadAd() {
        this.adNative.loadBannerAd(this.adSlot, new BannerAdListenerAdapter(this, this.adLoader.getAdListener()));
    }

    @Override
    public BannerAdLoader getAdLoader() {
        return this.adLoader;
    }

    public BannerAdSlot getAdSlot() {
        return adSlot;
    }
}
