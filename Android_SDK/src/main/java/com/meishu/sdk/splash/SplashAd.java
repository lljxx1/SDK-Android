package com.meishu.sdk.splash;

import android.view.View;


public interface SplashAd {
    /**
     * 获取广告view
     *
     * @return
     */
    View getAdView();

    void setInteractionListener(SplashInteractionListener listener);

    SplashInteractionListener getInteractionListener();
}
