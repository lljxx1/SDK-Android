package com.meishu.sdk.reward.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.meishu.sdk.reward.RewardVideoAdListener;

public class RewardVideoListenerAdapter implements TTAdNative.RewardVideoAdListener {

    private RewardVideoAdListener apiAdListener;
    private CSJRewardVideoAdWrapper adWrapper;

    public RewardVideoListenerAdapter(@NonNull CSJRewardVideoAdWrapper adWrapper, RewardVideoAdListener apiAdListener) {
        this.adWrapper = adWrapper;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onError(int i, String s) {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }

    @Override
    public void onRewardVideoAdLoad(TTRewardVideoAd ttRewardVideoAd) {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdLoaded(new RewardVideoAdAdapter(this.adWrapper, ttRewardVideoAd));
        }
    }

    @Override
    public void onRewardVideoCached() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onVideoCached();
        }
    }
}
