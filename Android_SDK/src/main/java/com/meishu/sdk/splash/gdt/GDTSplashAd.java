package com.meishu.sdk.splash.gdt;

import android.view.View;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class GDTSplashAd extends BaseAdData implements SplashAd {
    private View adView;
    private SplashInteractionListener interactionListener;

    @Override
    public View getAdView() {
        return this.adView;
    }

    public void setAdView(View adView) {
        this.adView = adView;
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
