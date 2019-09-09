package com.meishu.sdk.interstitial.meishu;

import com.meishu.sdk.meishu_ad.interstitial.AdListener;
import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class MeishuAdListenerAdapter implements AdListener {
    private InterstitialAdListener meishuAdListener;

    public MeishuAdListenerAdapter(InterstitialAdListener meishuAdListener) {
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onLoaded(NativeInterstitialAd nativeAd) {
        if (meishuAdListener != null) {
            meishuAdListener.onAdLoaded(new MeishuInterstitialAdAdapter(nativeAd,this.meishuAdListener));
        }
    }

    @Override
    public void onADExposure() {
        if (meishuAdListener != null) {
            meishuAdListener.onAdExposure();
        }
    }

    @Override
    public void onADClosed() {
        if (meishuAdListener != null) {
            meishuAdListener.onAdClosed();
        }
    }
}
