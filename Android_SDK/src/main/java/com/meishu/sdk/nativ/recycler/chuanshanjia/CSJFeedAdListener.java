package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.meishu.sdk.nativ.recycler.RecyclerAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;

import java.util.ArrayList;
import java.util.List;

public class CSJFeedAdListener implements TTAdNative.FeedAdListener {
    private RecyclerAdListener meishuAdListener;
    private CSJTTAdNativeWrapper adNativeWrapper;

    public CSJFeedAdListener(@NonNull CSJTTAdNativeWrapper adNativeWrapper, RecyclerAdListener meishuAdListener) {
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
            List<RecyclerAdData> meishuAdDatas = new ArrayList<>();
            for (TTFeedAd ttFeedAd : list) {
                meishuAdDatas.add(new CSJRecyclerAdDataAdapter(this.adNativeWrapper, ttFeedAd));
            }
            this.meishuAdListener.onAdLoaded(meishuAdDatas);
        }
    }
}
