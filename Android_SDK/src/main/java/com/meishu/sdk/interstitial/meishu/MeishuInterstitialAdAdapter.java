package com.meishu.sdk.interstitial.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class MeishuInterstitialAdAdapter implements InterstitialAd {
    private NativeInterstitialAd interstitialAd;
    private InterstitialAdListener apiAdListener;

    public MeishuInterstitialAdAdapter(@NonNull NativeInterstitialAd interstitialAd,
                                       InterstitialAdListener adListener) {
        this.interstitialAd = interstitialAd;
        this.apiAdListener = adListener;
    }

    private InteractionListener apiInteractionListener;

    @Override
    public void setInteractionListener(InteractionListener listener) {
        this.apiInteractionListener = listener;
        interstitialAd.setInteractionListener(new MeishuInteractionListener(interstitialAd, listener));
    }

    @Override
    public InteractionListener getInteractionListener() {
        return apiInteractionListener;
    }

    @Override
    public InterstitialAdListener getAdListener() {
        return this.apiAdListener;
    }
}
