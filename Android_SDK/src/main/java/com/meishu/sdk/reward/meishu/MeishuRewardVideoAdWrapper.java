package com.meishu.sdk.reward.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.reward.RewardVideoLoader;

public class MeishuRewardVideoAdWrapper extends BaseMeishuWrapper {

    private NativeAdSlot adSlot;
    private AdNative adNative;
    private RewardVideoLoader adLoader;

    public MeishuRewardVideoAdWrapper(@NonNull RewardVideoLoader adLoader, @NonNull NativeAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader=adLoader;
        this.adSlot = adSlot;
        this.adNative = new AdNative(adLoader.getActivity());
    }

    @Override
    public void loadAd() {
        this.adNative.loadRewardVideoAd(this.adSlot, new AdListenerAdapter(this,this.adLoader.getApiAdListener()));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public NativeAdSlot getAdSlot() {
        return adSlot;
    }
}
