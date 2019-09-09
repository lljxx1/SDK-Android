package com.meishu.sdk.meishu_ad.splash;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.meishu_ad.NativeDownloadListener;

public class SplashAdImpl implements NativeSplashAd {
    private View adView;
    private InteractionListener interactionListener;
    private NativeDownloadListener downloadListener;
    private SplashAdSlot adSlot;

    public SplashAdImpl(@NonNull SplashAdSlot adSlot) {
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

    public InteractionListener getInteractionListener() {
        return interactionListener;
    }

    public void setAdView(View adView) {
        this.adView = adView;
    }

    @Override
    public SplashAdSlot getAdSlot() {
        return adSlot;
    }
}
