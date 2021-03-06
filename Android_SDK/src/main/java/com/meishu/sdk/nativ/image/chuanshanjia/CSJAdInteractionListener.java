package com.meishu.sdk.nativ.image.chuanshanjia;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.meishu.sdk.nativ.image.ImageAdInteractionListener;

public class CSJAdInteractionListener implements TTNativeAd.AdInteractionListener {
    private static final String TAG = "CSJAdInteractionListene";
    private ImageAdInteractionListener meishuInteractionListener;
    private CSJImageAdDataAdapter csjNativeAdDataAdapter;

    public CSJAdInteractionListener(@NonNull CSJImageAdDataAdapter csjNativeAdDataAdapter, ImageAdInteractionListener meishuInteractionListener) {
        this.csjNativeAdDataAdapter=csjNativeAdDataAdapter;
        this.meishuInteractionListener = meishuInteractionListener;
    }

    @Override
    public void onAdClicked(View view, TTNativeAd ttNativeAd) {
        meishuInteractionListener.onAdClicked();
    }

    @Override
    public void onAdCreativeClick(View view, TTNativeAd ttNativeAd) {
//        meishuInteractionListener.onAdClicked();
        Log.d(TAG, "onAdCreativeClick: 创意被点击");
    }

    @Override
    public void onAdShow(TTNativeAd ttNativeAd) {
        if(this.csjNativeAdDataAdapter.getAdListener()!=null){
            this.csjNativeAdDataAdapter.getAdListener().onAdExposure();
        }
    }
}
