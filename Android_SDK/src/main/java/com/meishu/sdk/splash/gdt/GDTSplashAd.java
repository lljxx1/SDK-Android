package com.meishu.sdk.splash.gdt;

import android.view.View;

import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class GDTSplashAd implements SplashAd {
    private View adView;
    private SplashInteractionListener interactionListener;

    public GDTSplashAd(View adView) {
        this.adView = adView;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    @Override
    public void setInteractionListener(SplashInteractionListener listener) {
        this.interactionListener = listener;
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
