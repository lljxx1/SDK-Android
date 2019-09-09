package com.meishu.sdk.interstitial.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTInteractionAd;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.interstitial.InterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class CSJInterstitialAd implements InterstitialAd {
    private static final String TAG = "CSJInterstitialAd";
    private TTInteractionAd ttInteractionAd;
    private InteractionListener interactionListener;
    private InterstitialAdListener adListener;
    private CSJTTAdNativeWrapper adWrapper;

    public CSJInterstitialAd(@NonNull CSJTTAdNativeWrapper adWrapper, @NonNull TTInteractionAd ttInteractionAd, InterstitialAdListener adListener) {
        this.adWrapper =adWrapper;
        this.ttInteractionAd = ttInteractionAd;
        this.adListener = adListener;
    }

    @Override
    public void setInteractionListener(InteractionListener listener) {
        this.interactionListener = listener;
        if (listener != null && this.ttInteractionAd != null) {
            this.ttInteractionAd.setAdInteractionListener(new CSJInteractionListenerImpl(this,listener));
        }
    }

    @Override
    public InteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    @Override
    public InterstitialAdListener getAdListener() {
        return this.adListener;
    }

    public void showAd(Activity activity) {
        if (activity != null) {
            ttInteractionAd.showInteractionAd(activity);
        } else {
            Log.e(TAG, "showAd: ", new Exception("内部参数错误，无法打开插屏广告"));
        }
    }

    public CSJTTAdNativeWrapper getAdWrapper() {
        return adWrapper;
    }
}
