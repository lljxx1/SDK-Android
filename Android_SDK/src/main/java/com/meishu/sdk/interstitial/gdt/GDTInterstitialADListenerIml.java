package com.meishu.sdk.interstitial.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.interstitial.InterstitialADListener;
import com.qq.e.comm.util.AdError;

public class GDTInterstitialADListenerIml implements InterstitialADListener {
    private static final String TAG = "GDTInterstitialADListen";
    private InterstitialAdListener interstitialADListener;

    private GDTInterstitialAdWrapper interstitialAdWrapper;

    public GDTInterstitialADListenerIml(@NonNull GDTInterstitialAdWrapper interstitialAdWrapper, InterstitialAdListener interstitialADListener) {
        this.interstitialADListener = interstitialADListener;
        this.interstitialAdWrapper = interstitialAdWrapper;
    }

    private InterstitialAd interstitialAd;

    @Override
    public void onADReceive() {
        this.interstitialAd = new GDTInterstitialAd(this.interstitialADListener);
        if (this.interstitialADListener != null) {
            this.interstitialADListener.onAdLoaded(interstitialAd);
            this.interstitialAdWrapper.showAD();
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        if(interstitialADListener!=null){
            interstitialADListener.onAdError();
        }
    }

    @Override
    public void onADOpened() {
        Log.d(TAG, "onADOpened: ");
    }

    @Override
    public void onADExposure() {
        interstitialADListener.onAdExposure();
    }

    @Override
    public void onADClicked() {
        if (this.interstitialAdWrapper.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(this.interstitialAdWrapper.getActivity(),this.interstitialAdWrapper.getSdkAdInfo().getClk(), new DefaultHttpGetWithNoHandlerCallback());
        }
        if (this.interstitialAd != null && this.interstitialAd.getInteractionListener() != null) {
            this.interstitialAd.getInteractionListener().onAdClicked();
        }
    }

    @Override
    public void onADLeftApplication() {
        Log.d(TAG, "onADLeftApplication: ");
    }

    @Override
    public void onADClosed() {
        interstitialADListener.onAdClosed();
    }
}
