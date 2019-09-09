package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.splash.SplashInteractionListener;

public class MeishuSplashInteractionListener implements NativeSplashAd.InteractionListener {
    private SplashInteractionListener interactionListener;
    private NativeSplashAd nativeSplashAd;

    public MeishuSplashInteractionListener(@NonNull NativeSplashAd nativeSplashAd, SplashInteractionListener interactionListener) {
        this.nativeSplashAd = nativeSplashAd;
        this.interactionListener = interactionListener;
    }


    @Override
    public void onAdClicked() {
        if (interactionListener != null) {
            interactionListener.onAdClicked();
        }

        ClickHandler.handleClick(nativeSplashAd);
    }

    @Override
    public void onSkip() {

    }
}
