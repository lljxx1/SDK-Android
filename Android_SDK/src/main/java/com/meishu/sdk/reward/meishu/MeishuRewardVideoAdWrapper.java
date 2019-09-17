package com.meishu.sdk.reward.meishu;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.reward.RewardVideoAdDelegate;
import com.meishu.sdk.reward.RewardVideoAdListener;

public class MeishuRewardVideoAdWrapper implements RewardVideoAdDelegate {

    private Activity activity;
    private NativeAdSlot adSlot;
    private RewardVideoAdListener adListener;
    private AdNative adNative;
    private DelegateChain next;

    public MeishuRewardVideoAdWrapper(@NonNull Activity activity, @NonNull NativeAdSlot adSlot,@NonNull RewardVideoAdListener adListener) {
        this.activity = activity;
        this.adSlot = adSlot;
        this.adListener = adListener;
        this.adNative = new AdNative(activity);
    }

    @Override
    public void loadAd() {
        this.adNative.loadRewardVideoAd(this.adSlot, new AdListenerAdapter(this,this.adListener));
    }

    public Activity getActivity() {
        return activity;
    }

    public NativeAdSlot getAdSlot() {
        return adSlot;
    }
}
