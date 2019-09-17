package com.meishu.sdk.interstitial.meishu;

import android.app.Activity;

import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdSlot;
import com.meishu.sdk.interstitial.InterstitialAdDelegate;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class MeishuAdNativeWrapper implements InterstitialAdDelegate {
    private AdNative adNative;
    private InterstitialAdSlot adSlot;
    private InterstitialAdListener adListener;
    private Activity activity;

    public MeishuAdNativeWrapper(Activity activity, InterstitialAdSlot adSlot, InterstitialAdListener adListener) {
        this.activity = activity;
        this.adNative = new AdNative(activity);
        this.adSlot = adSlot;
        this.adListener = adListener;
    }

    @Override
    public void loadAd() {
        adNative.loadInstitialAd(adSlot, new MeishuAdListenerAdapter(this, this.adListener));
    }

    @Override
    public void destroy() {
        //do nothing
    }

    public InterstitialAdSlot getAdSlot() {
        return adSlot;
    }

    public Activity getActivity() {
        return activity;
    }
}
