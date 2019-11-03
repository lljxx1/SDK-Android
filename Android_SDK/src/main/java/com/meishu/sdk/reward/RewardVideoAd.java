package com.meishu.sdk.reward;

public interface RewardVideoAd {
    void showAd();

    void destroy();

    void setInteractionListener(RewardAdInteractionListener interactionListener);

    void setMediaListener(RewardAdMediaListener rewardAdMediaListener);
}
