package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class MeishuSplashAdAdapter implements com.meishu.sdk.splash.SplashAd {
    private NativeSplashAd nativeAd;
    private SplashInteractionListener interactionListener;

    public MeishuSplashAdAdapter(@NonNull NativeSplashAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    @Override
    public View getAdView() {
        return this.nativeAd.getAdView();
    }

    @Override
    public void setInteractionListener(SplashInteractionListener listener) {
        this.interactionListener = listener;
        nativeAd.setInteractionListener(new MeishuSplashInteractionListener(this.nativeAd, listener));
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
