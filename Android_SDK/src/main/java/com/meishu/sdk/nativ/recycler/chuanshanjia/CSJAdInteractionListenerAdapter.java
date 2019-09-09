package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJAdInteractionListenerAdapter implements TTNativeAd.AdInteractionListener {

    private CSJAdDataAdapter adData;

    private AdInteractionListener meishuInteractionListener;

    public CSJAdInteractionListenerAdapter(@NonNull CSJAdDataAdapter adData, AdInteractionListener meishuInteractionListener) {
        this.adData = adData;
        this.meishuInteractionListener = meishuInteractionListener;
    }

    @Override
    public void onAdClicked(View view, TTNativeAd ttNativeAd) {
        if (this.adData.getAdWrapper() != null) {
            HttpUtil.asyncGetWithWebViewUA(adData.getAdWrapper().getActivity(), this.adData.getAdWrapper().getSdkAdInfo().getClk(), new DefaultHttpGetWithNoHandlerCallback());
        }
        if (this.meishuInteractionListener != null) {
            this.meishuInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onAdCreativeClick(View view, TTNativeAd ttNativeAd) {
        if (this.meishuInteractionListener != null) {
            this.meishuInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onAdShow(TTNativeAd ttNativeAd) {
        if (this.adData.getAdListener() != null) {
            this.adData.getAdListener().onAdExposure();
        }
    }
}