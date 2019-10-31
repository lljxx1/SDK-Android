package com.meishu.sdk.nativ.image.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.meishu.sdk.nativ.image.ImageAdData;
import com.meishu.sdk.nativ.image.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

public class CSJNativeAdListener implements TTAdNative.NativeAdListener {

    private static final String TAG = "CSJNativeAdListener";
    private NativeAdListener meishuAdListener;
    private CSJTTAdNativeWrapper adWrapper;

    public CSJNativeAdListener(@NonNull CSJTTAdNativeWrapper adWrapper, NativeAdListener meishuAdListener) {
        this.adWrapper = adWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onError(int i, String s) {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onAdError();
        }
    }

    @Override
    public void onNativeAdLoad(List<TTNativeAd> list) {
        if (list != null && this.meishuAdListener != null) {
            List<ImageAdData> adDatas = new ArrayList<>();
            for (TTNativeAd ttNativeAd : list) {
                adDatas.add(new CSJImageAdDataAdapter(ttNativeAd, this));
            }
            this.meishuAdListener.onAdLoaded(adDatas);
        }
    }

    public void onAdExposure() {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onAdExposure();
        }
    }

    /*public void onAdClosed() {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onAdClosed();
        }
    }*/
}
