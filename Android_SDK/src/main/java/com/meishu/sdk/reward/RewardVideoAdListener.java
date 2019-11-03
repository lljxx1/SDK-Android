package com.meishu.sdk.reward;

public interface RewardVideoAdListener {

    void onAdLoaded(RewardVideoAd ad);

    void onVideoCached();

    void onAdExposure();

    void onReward();

    void onAdClosed();

    void onAdError();
}
