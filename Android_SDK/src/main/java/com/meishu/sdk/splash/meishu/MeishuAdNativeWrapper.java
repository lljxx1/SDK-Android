package com.meishu.sdk.splash.meishu;

import android.app.Activity;

import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.splash.SplashAdSlot;
import com.meishu.sdk.splash.SplashAdDelegate;
import com.meishu.sdk.splash.SplashAdListener;

public class MeishuAdNativeWrapper implements SplashAdDelegate {

    private AdNative adNative;
    private SplashAdListener splashAdListener;
    private SplashAdSlot adSlot;

    public MeishuAdNativeWrapper(Activity activity, SplashAdSlot adSlot, SplashAdListener splashAdListener) {
        adNative = new AdNative(activity);
        this.adSlot = adSlot;
        this.splashAdListener = splashAdListener;
    }

    @Override
    public void loadAd() {
        adNative.loadSplashAd(adSlot, new SplashAdListenerAdapter(this.splashAdListener));
    }
}
