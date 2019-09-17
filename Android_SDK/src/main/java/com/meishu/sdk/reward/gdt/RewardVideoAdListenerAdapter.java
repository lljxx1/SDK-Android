package com.meishu.sdk.reward.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.reward.RewardVideoAdListener;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;

public class RewardVideoAdListenerAdapter implements RewardVideoADListener {
    private static final String TAG = "RewardVideoAdListenerAd";
    private RewardVideoAdListener apiAdListener;
    private GDTRewardVideoAdWrapper adWrapper;

    public RewardVideoAdListenerAdapter(@NonNull GDTRewardVideoAdWrapper adWrapper, RewardVideoAdListener apiAdListener) {
        this.adWrapper = adWrapper;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onADLoad() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdLoaded(new RewardVideoAdAdapter(adWrapper));
        }
    }

    @Override
    public void onVideoCached() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onVideoCached();
        }
    }

    @Override
    public void onADShow() {
        Log.d(TAG, "onADShow: ");
    }

    @Override
    public void onADExpose() {
        Log.d(TAG, "onADExpose: ");
    }

    @Override
    public void onReward() {
        Log.d(TAG, "onReward: ");
    }

    @Override
    public void onADClick() {
        Log.d(TAG, "onADClick: ");
    }

    @Override
    public void onVideoComplete() {
        Log.d(TAG, "onVideoComplete: ");
    }

    @Override
    public void onADClose() {
        Log.d(TAG, "onADClose: ");
    }

    @Override
    public void onError(AdError adError) {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
