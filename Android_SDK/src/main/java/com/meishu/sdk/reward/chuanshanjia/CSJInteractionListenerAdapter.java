package com.meishu.sdk.reward.chuanshanjia;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.meishu.sdk.reward.RewardAdInteractionListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJInteractionListenerAdapter implements TTRewardVideoAd.RewardAdInteractionListener {
    private static final String TAG = "CSJInteractionListenerA";
    private RewardVideoAdAdapter ad;
    private RewardAdInteractionListener apiInteractionListener;

    public CSJInteractionListenerAdapter(@NonNull RewardVideoAdAdapter ad, RewardAdInteractionListener apiInteractionListener) {
        this.ad = ad;
        this.apiInteractionListener = apiInteractionListener;
    }

    @Override
    public void onAdShow() {
        if (this.ad.getAdWrapper().getSdkRewardAdListener() != null) {
            this.ad.getAdWrapper().getSdkRewardAdListener().onAdExposure();
        }
    }

    @Override
    public void onAdVideoBarClick() {
        if (ad.getAdWrapper() != null && ad.getAdWrapper().getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    ad.getAdWrapper().getActivity(),
                    ad.getAdWrapper().getSdkAdInfo().getClk(),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (this.apiInteractionListener != null) {
            this.apiInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onAdClose() {
        if (this.ad.getAdWrapper().getAdLoader().getApiAdListener() != null) {
            this.ad.getAdWrapper().getAdLoader().getApiAdListener().onAdClosed();
        }
    }

    @Override
    public void onVideoComplete() {
        if (this.ad.getAdWrapper().getApiRewardAdMediaListener() != null) {
            this.ad.getAdWrapper().getApiRewardAdMediaListener().onVideoCompleted();
        }
    }

    @Override
    public void onVideoError() {
        Log.d(TAG, "onVideoError: ");
    }

    @Override
    public void onRewardVerify(boolean b, int i, String s) {
        if (this.ad.getAdWrapper().getAdLoader().getApiAdListener() != null) {
            this.ad.getAdWrapper().getAdLoader().getApiAdListener().onReward();
        }
    }

    @Override
    public void onSkippedVideo() {
        Log.d(TAG, "onSkippedVideo: ");
    }
}
