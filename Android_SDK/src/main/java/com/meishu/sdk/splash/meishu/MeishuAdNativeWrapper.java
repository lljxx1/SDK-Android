package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.splash.SplashAdSlot;
import com.meishu.sdk.splash.SplashAdLoader;

public class MeishuAdNativeWrapper extends BaseMeishuWrapper {

    private AdNative adNative;
    private SplashAdSlot adSlot;
    private SplashAdLoader adLoader;

    public MeishuAdNativeWrapper(@NonNull SplashAdLoader adLoader, SplashAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader = adLoader;
        adNative = new AdNative(adLoader.getActivity());
        this.adSlot = adSlot;
    }

    @Override
    public void loadAd() {
        adNative.loadSplashAd(adSlot, new SplashAdListenerAdapter(this, this.adLoader.getApiAdListener()));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public SplashAdSlot getAdSlot() {
        return adSlot;
    }

}
