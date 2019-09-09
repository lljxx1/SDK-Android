package com.meishu.sdk.interstitial.meishu;

import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.service.ClickHandler;

public class MeishuInteractionListener implements NativeInterstitialAd.InteractionListener {
    private InteractionListener apiInteractionListener;
    private NativeInterstitialAd nativeAd;

    public MeishuInteractionListener(NativeInterstitialAd nativeAd, InteractionListener apiInteractionListener) {
        this.nativeAd=nativeAd;
        this.apiInteractionListener = apiInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if(apiInteractionListener!=null){
            apiInteractionListener.onAdClicked();
        }
        ClickHandler.handleClick(nativeAd);
    }
}
