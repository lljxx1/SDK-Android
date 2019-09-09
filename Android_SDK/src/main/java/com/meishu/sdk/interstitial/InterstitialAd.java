package com.meishu.sdk.interstitial;

public interface InterstitialAd {

    void setInteractionListener(InteractionListener listener);

    InteractionListener getInteractionListener();

    InterstitialAdListener getAdListener();
}
