package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class MeishuSplashAdAdapter extends BaseAdData implements com.meishu.sdk.splash.SplashAd {
    private NativeSplashAd nativeAd;
    private SplashInteractionListener interactionListener;

    public MeishuSplashAdAdapter(@NonNull NativeSplashAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    @Override
    public View getAdView() {
        View adView =this.nativeAd.getAdView();
        if(adView!=null){
            ViewGroup parent = (ViewGroup) adView.getParent();
            if(parent!=null){
                parent.removeView(adView);
            }
            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
            touchContainer.setTouchPositionListener(new TouchPositionListener(this.nativeAd));
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
        nativeAd.setInteractionListener(new MeishuSplashInteractionListener(this.nativeAd, listener));
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
