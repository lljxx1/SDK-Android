package com.meishu.sdk.reward.meishu;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.meishu_ad.nativ.AdListener;
import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.reward.RewardVideoAd;
import com.meishu.sdk.reward.RewardVideoAdListener;

import java.util.List;

public class AdListenerAdapter implements AdListener {
    private static final String TAG = "AdListenerAdapter";
    private RewardVideoAdListener apiAdListener;
    private MeishuRewardVideoAdWrapper adWrapper;

    public AdListenerAdapter(@NonNull MeishuRewardVideoAdWrapper adWrapper, RewardVideoAdListener apiAdListener) {
        this.adWrapper = adWrapper;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(List<NativeAdData> adDatas) {
        if (this.apiAdListener != null && adDatas != null && adDatas.size() > 0) {
            this.apiAdListener.onAdLoaded(new RewardVideoAdAdapter(adWrapper,adDatas.get(0)));
            this.apiAdListener.onVideoCached();
        }
    }

    @Override
    public void onADExposure() {
        Log.d(TAG, "onADExposure: ");
    }

    @Override
    public void onAdError() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
