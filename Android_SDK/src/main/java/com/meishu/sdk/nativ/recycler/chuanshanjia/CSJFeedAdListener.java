package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.meishu.sdk.nativ.recycler.NativeAdData;
import com.meishu.sdk.nativ.recycler.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

public class CSJFeedAdListener implements TTAdNative.FeedAdListener {
    private NativeAdListener meishuAdListener;
    private CSJTTAdNativeWrapper adNativeWrapper;

    public CSJFeedAdListener(@NonNull CSJTTAdNativeWrapper adNativeWrapper, NativeAdListener meishuAdListener) {
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
    public void onFeedAdLoad(List<TTFeedAd> list) {
        if (list != null && this.meishuAdListener != null) {
            List<NativeAdData> meishuAdDatas = new ArrayList<>();
            for (TTFeedAd ttFeedAd : list) {
                meishuAdDatas.add(new CSJNativeAdDataAdapter(this.adNativeWrapper, ttFeedAd));
            }
            this.meishuAdListener.onAdLoaded(meishuAdDatas);
        }
    }
}
