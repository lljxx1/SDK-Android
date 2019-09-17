package com.meishu.sdk.reward.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
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
}
