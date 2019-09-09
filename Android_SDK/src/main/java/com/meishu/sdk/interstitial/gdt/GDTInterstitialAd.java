package com.meishu.sdk.interstitial.gdt;

import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class GDTInterstitialAd implements InterstitialAd {

    private InteractionListener interactionListener;
    private InterstitialAdListener adListener;

    public GDTInterstitialAd(InterstitialAdListener adListener) {
        this.adListener = adListener;
    }

    @Override
    public void setInteractionListener(InteractionListener listener) {
        this.interactionListener = listener;
    }

    @Override
    public InteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    @Override
    public InterstitialAdListener getAdListener() {
        return this.adListener;
    }
}
