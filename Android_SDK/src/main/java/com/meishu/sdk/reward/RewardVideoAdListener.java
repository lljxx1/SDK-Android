package com.meishu.sdk.reward;

public interface RewardVideoAdListener {

    void onAdLoaded(RewardVideoAd ad);

    void onVideoCached();

    void onAdError();
}
