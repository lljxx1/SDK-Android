package com.meishu.sdk.interstitial.meishu;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;

public class MeishuInterstitialAdAdapter implements InterstitialAd {
    private NativeInterstitialAd interstitialAd;
    private InterstitialAdListener apiAdListener;
    private InteractionListener apiInteractionListener;
    private View adView;

    public MeishuInterstitialAdAdapter(@NonNull NativeInterstitialAd interstitialAd,
                                       InterstitialAdListener adListener) {
        this.interstitialAd = interstitialAd;
        this.apiAdListener = adListener;
    }

    public View getAdView(){
        return this.adView;
    }

    public void setAdView(View adView) {
        this.adView = adView;
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
