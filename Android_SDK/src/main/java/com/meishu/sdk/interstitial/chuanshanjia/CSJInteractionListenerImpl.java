package com.meishu.sdk.interstitial.chuanshanjia;

import com.bytedance.sdk.openadsdk.TTInteractionAd;
import com.meishu.sdk.interstitial.InteractionListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJInteractionListenerImpl implements TTInteractionAd.AdInteractionListener {
    private static final String TAG = "CSJInteractionListener";
    private InteractionListener meishuInteractionListener;

    private CSJInterstitialAd interstitialAd;

    public CSJInteractionListenerImpl(CSJInterstitialAd interstitialAd, InteractionListener meishuInteractionListener) {
        this.interstitialAd = interstitialAd;
        this.meishuInteractionListener = meishuInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if (this.interstitialAd.getAdWrapper() != null) {
            HttpUtil.asyncGetWithWebViewUA(this.interstitialAd.getAdWrapper().getActivity(), this.interstitialAd.getAdWrapper().getSdkAdInfo().getClk(), new DefaultHttpGetWithNoHandlerCallback());
        }
        if (this.meishuInteractionListener != null) {
            this.meishuInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onAdShow() {
        if (this.interstitialAd != null && this.interstitialAd.getAdListener() != null) {
            this.interstitialAd.getAdListener().onAdExposure();
        }
    }

    @Override
    public void onAdDismiss() {
        if (this.interstitialAd != null && this.interstitialAd.getAdListener() != null) {
            this.interstitialAd.getAdListener().onAdClosed();
        }
    }
}
