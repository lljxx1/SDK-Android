package com.meishu.sdk.interstitial.meishu;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class MeishuInterstitialAdAdapter implements InterstitialAd {
    private NativeInterstitialAd interstitialAd;
    private InterstitialAdListener apiAdListener;
    private InteractionListener apiInteractionListener;

    public MeishuInterstitialAdAdapter(@NonNull NativeInterstitialAd interstitialAd,
                                       InterstitialAdListener adListener) {
        this.interstitialAd = interstitialAd;
        this.apiAdListener = adListener;
    }

    public View getAdView(){
        View adView = interstitialAd.getAdView();
        ViewGroup parent = (ViewGroup) adView.getParent();
        if(parent!=null){
            parent.removeView(adView);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this.interstitialAd));
        touchContainer.addView(adView);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        adView=touchContainer;
        return adView;
    }

    @Override
    public void setInteractionListener(InteractionListener listener) {
        this.apiInteractionListener = listener;
        interstitialAd.setInteractionListener(new MeishuInteractionListener(interstitialAd, listener));
    }

    @Override
    public InteractionListener getInteractionListener() {
        return apiInteractionListener;
    }

    @Override
    public InterstitialAdListener getAdListener() {
        return this.apiAdListener;
    }
}
