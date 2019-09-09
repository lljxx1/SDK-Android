package com.meishu.sdk.meishu_ad.interstitial;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.meishu_ad.NativeDownloadListener;

public class InterstitialAdImpl implements NativeInterstitialAd {
    private View adView;
    private InteractionListener interactionListener;
    private NativeDownloadListener downloadListener;
    private InterstitialAdSlot adSlot;

    public InterstitialAdImpl(@NonNull InterstitialAdSlot adSlot) {
        this.adSlot = adSlot;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    @Override
    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @Override
    public void setDownloadListener(NativeDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public int getInteractionType() {
        return this.adSlot.getInteractionType();
    }

    @Override
    public InterstitialAdSlot getAdSlot() {
        return this.adSlot;
    }

    public InteractionListener getInteractionListener() {
        return interactionListener;
    }

    public void setAdView(View adView) {
        this.adView = adView;
    }

}
