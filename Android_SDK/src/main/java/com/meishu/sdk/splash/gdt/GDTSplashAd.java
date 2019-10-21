package com.meishu.sdk.splash.gdt;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class GDTSplashAd extends BaseAdData implements SplashAd {
    private View adView;
    private SplashInteractionListener interactionListener;

    public GDTSplashAd(@NonNull View adView) {
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
