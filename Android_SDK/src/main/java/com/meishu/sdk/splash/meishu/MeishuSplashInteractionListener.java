package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.splash.SplashInteractionListener;

public class MeishuSplashInteractionListener implements NativeSplashAd.InteractionListener {
    private SplashInteractionListener interactionListener;
    private MeishuSplashAdAdapter meishuSplashAdAdapter;

    public MeishuSplashInteractionListener(@NonNull MeishuSplashAdAdapter meishuSplashAdAdapter, SplashInteractionListener interactionListener) {
        this.meishuSplashAdAdapter = meishuSplashAdAdapter;
        this.interactionListener = interactionListener;
    }


    @Override
    public void onAdClicked() {
        if (interactionListener != null) {
            interactionListener.onAdClicked();
        }
        this.meishuSplashAdAdapter.setClicked(true);
        ClickHandler.handleClick(meishuSplashAdAdapter.getNativeAd());
    }

    @Override
    public void onSkip() {

    }
}
