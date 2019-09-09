package com.meishu.sdk.splash.chuanshanjia;

import android.view.View;

import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class CSJSplashAd implements SplashAd {
    private TTSplashAd ttSplashAd;
    private SplashInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;

    public CSJSplashAd(SdkAdInfo sdkAdInfo, TTSplashAd ttSplashAd) {
        this.sdkAdInfo = sdkAdInfo;
        this.ttSplashAd = ttSplashAd;
    }

    @Override
    public View getAdView() {
        if (ttSplashAd != null) {
            return ttSplashAd.getSplashView();
        } else {
            return null;
        }
    }

    @Override
    public void setInteractionListener(SplashInteractionListener listener) {
        this.interactionListener = listener;
        if (ttSplashAd != null && listener != null) {
            ttSplashAd.setSplashInteractionListener(new CSJSplashInteractionListenerImpl(this, listener));
        }
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    public SdkAdInfo getSdkAdInfo() {
        return this.sdkAdInfo;
    }
}
