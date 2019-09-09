package com.meishu.sdk.interstitial.chuanshanjia;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTInteractionAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;

public class CSJInteractionAdListenerImpl implements TTAdNative.InteractionAdListener {
    private static final String TAG = "CSJInteractionAdListene";
    private final CSJTTAdNativeWrapper adNativeWrapper;
    private InterstitialAdListener meishuAdListener;

    public CSJInteractionAdListenerImpl(CSJTTAdNativeWrapper adNativeWrapper, InterstitialAdListener meishuAdListener) {
        this.adNativeWrapper = adNativeWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onError(int i, String s) {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onAdError();
        }
    }

    @Override
    public void onInteractionAdLoad(TTInteractionAd ttInteractionAd) {
        CSJInterstitialAd interstitialAd = new CSJInterstitialAd(adNativeWrapper, ttInteractionAd, this.meishuAdListener);
        meishuAdListener.onAdLoaded(interstitialAd);
        interstitialAd.showAd(adNativeWrapper.getActivity());
    }
}
