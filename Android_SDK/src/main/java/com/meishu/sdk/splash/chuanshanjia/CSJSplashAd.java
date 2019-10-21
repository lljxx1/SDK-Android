package com.meishu.sdk.splash.chuanshanjia;

import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class CSJSplashAd extends BaseAdData implements SplashAd {
    private TTSplashAd ttSplashAd;
    private SplashInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;

    public CSJSplashAd(SdkAdInfo sdkAdInfo, TTSplashAd ttSplashAd) {
        this.sdkAdInfo = sdkAdInfo;
        this.ttSplashAd = ttSplashAd;
    }

    @Override
    public View getAdView() {
        View adView =null;
        if (ttSplashAd != null) {
            adView= ttSplashAd.getSplashView();

            ViewGroup parent = (ViewGroup) adView.getParent();
            if(parent!=null){
                parent.removeView(adView);
            }
            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
            touchContainer.setTouchPositionListener(new TouchPositionListener(this));
            touchContainer.addView(adView);
            if(parent!=null){
                parent.addView(touchContainer);
            }
            adView=touchContainer;
        }
        return adView;
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
