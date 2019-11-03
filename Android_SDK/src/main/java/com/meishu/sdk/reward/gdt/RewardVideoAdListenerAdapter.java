package com.meishu.sdk.reward.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.reward.RewardVideoAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
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
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdExposure();
        }
    }

    @Override
    public void onReward() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onReward();
        }
    }

    @Override
    public void onADClick() {
        if (adWrapper.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    adWrapper.getActivity(),
                    adWrapper.getSdkAdInfo().getClk(),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (this.adWrapper.getApiInteractionListener() != null) {
            this.adWrapper.getApiInteractionListener().onAdClicked();
        }
    }

    @Override
    public void onVideoComplete() {
        if (this.adWrapper.getApiRewardAdMediaListener() != null) {
            this.adWrapper.getApiRewardAdMediaListener().onVideoCompleted();
        }
    }

    @Override
    public void onADClose() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdClosed();
        }
    }

    @Override
    public void onError(AdError adError) {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
