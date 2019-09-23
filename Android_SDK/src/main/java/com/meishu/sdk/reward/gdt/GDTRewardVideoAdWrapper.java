package com.meishu.sdk.reward.gdt;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.reward.RewardVideoLoader;
import com.qq.e.ads.rewardvideo.RewardVideoAD;

public class GDTRewardVideoAdWrapper extends BaseSdkAdWrapper {

    private RewardVideoAD rewardVideoAd;
    private RewardVideoLoader adLoader;

    public GDTRewardVideoAdWrapper(@NonNull RewardVideoLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(),sdkAdInfo);
        this.adLoader=adLoader;
        this.rewardVideoAd = new RewardVideoAD(adLoader.getActivity(), sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new RewardVideoAdListenerAdapter(this, adLoader.getApiAdListener()));
    }

    @Override
    public void loadAd() {
        this.rewardVideoAd.loadAD();
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public void showAd() {
        this.rewardVideoAd.showAD();
    }

    public boolean hasShown() {
        return this.rewardVideoAd.hasShown();
    }
}
