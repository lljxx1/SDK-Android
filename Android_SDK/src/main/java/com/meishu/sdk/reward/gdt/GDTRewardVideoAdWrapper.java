package com.meishu.sdk.reward.gdt;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.reward.RewardVideoAdDelegate;
import com.meishu.sdk.reward.RewardVideoAdListener;
import com.qq.e.ads.rewardvideo.RewardVideoAD;

public class GDTRewardVideoAdWrapper implements RewardVideoAdDelegate, DelegateChain {

    private Activity activity;
    private SdkAdInfo sdkAdInfo;
    private RewardVideoAD rewardVideoAd;
    private DelegateChain next;

    public GDTRewardVideoAdWrapper(@NonNull Activity activity, @NonNull SdkAdInfo sdkAdInfo, @NonNull RewardVideoAdListener adListener) {
        this.activity = activity;
        this.sdkAdInfo = sdkAdInfo;
        this.rewardVideoAd = new RewardVideoAD(activity, sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new RewardVideoAdListenerAdapter(this, adListener));
    }

    @Override
    public void loadAd() {
        this.rewardVideoAd.loadAD();
    }

    public void showAd() {
        this.rewardVideoAd.showAD();
    }

    public boolean hasShown() {
        return this.rewardVideoAd.hasShown();
    }

    @Override
    public void setNext(DelegateChain next) {
        this.next = next;
    }

    @Override
    public DelegateChain getNext() {
        return this.next;
    }

    @Override
    public SdkAdInfo getSdkAdInfo() {
        return this.sdkAdInfo;
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }
}
