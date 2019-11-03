package com.meishu.sdk.reward.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.meishu.sdk.reward.RewardAdInteractionListener;
import com.meishu.sdk.reward.RewardAdMediaListener;
import com.meishu.sdk.reward.RewardVideoAd;

public class RewardVideoAdAdapter implements RewardVideoAd {
    private TTRewardVideoAd ttRewardVideoAd;
    private CSJRewardVideoAdWrapper adWrapper;

    public RewardVideoAdAdapter(@NonNull CSJRewardVideoAdWrapper adWrapper, @NonNull TTRewardVideoAd ttRewardVideoAd) {
        this.adWrapper = adWrapper;
        this.ttRewardVideoAd = ttRewardVideoAd;
    }

    @Override
    public void showAd() {
        ttRewardVideoAd.showRewardVideoAd(this.adWrapper.getActivity());
    }

    @Override
    public void destroy() {
//do nothing
    }

    @Override
    public void setInteractionListener(RewardAdInteractionListener interactionListener) {
        this.ttRewardVideoAd.setRewardAdInteractionListener(new CSJInteractionListenerAdapter(this,interactionListener));
    }

    @Override
    public void setMediaListener(RewardAdMediaListener rewardAdMediaListener) {
        adWrapper.setApiRewardAdMediaListener(rewardAdMediaListener);
    }

    public CSJRewardVideoAdWrapper getAdWrapper() {
        return adWrapper;
    }
}
