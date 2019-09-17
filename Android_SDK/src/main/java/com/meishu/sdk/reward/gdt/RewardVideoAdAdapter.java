package com.meishu.sdk.reward.gdt;

import android.support.annotation.NonNull;

import com.meishu.sdk.reward.RewardVideoAd;

public class RewardVideoAdAdapter implements RewardVideoAd {
    private GDTRewardVideoAdWrapper adWrapper;

    public RewardVideoAdAdapter(@NonNull GDTRewardVideoAdWrapper adWrapper) {
        this.adWrapper = adWrapper;
    }

    @Override
    public void showAd() {
        adWrapper.showAd();
    }
}
