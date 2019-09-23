package com.meishu.sdk.interstitial.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.interstitial.InterstitialAdLoader;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdSlot;

public class MeishuAdNativeWrapper extends BaseMeishuWrapper {
    private AdNative adNative;
    private InterstitialAdLoader adLoader;
    private InterstitialAdSlot adSlot;

    public MeishuAdNativeWrapper(@NonNull InterstitialAdLoader adLoader, @NonNull InterstitialAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader = adLoader;
        this.adSlot = adSlot;
        this.adNative = new AdNative(adLoader.getActivity());
    }

    @Override
    public void loadAd() {
        adNative.loadInstitialAd(this.adSlot, new MeishuAdListenerAdapter(this, this.adLoader.getApiAdListener()));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public InterstitialAdSlot getAdSlot() {
        return adSlot;
    }

}
