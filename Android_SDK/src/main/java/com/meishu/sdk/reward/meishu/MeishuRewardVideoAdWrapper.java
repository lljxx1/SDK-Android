package com.meishu.sdk.reward.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.AdListener;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.reward.RewardVideoLoader;

public class MeishuRewardVideoAdWrapper extends BaseMeishuWrapper {

    private NativeAdSlot adSlot;
    private AdNative adNative;
    private RewardVideoLoader adLoader;
    private AdListener meishuAdListener;

    public MeishuRewardVideoAdWrapper(@NonNull RewardVideoLoader adLoader, @NonNull NativeAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader=adLoader;
        this.adSlot = adSlot;
        this.adNative = new AdNative(adLoader.getActivity());
    }

    @Override
    public void loadAd() {
        this.meishuAdListener = new AdListenerAdapter(this,this.adLoader.getApiAdListener());
        this.adNative.loadRewardVideoAd(this.adSlot, this.meishuAdListener);
    }

    @Override
    public RewardVideoLoader getAdLoader() {
        return this.adLoader;
    }

    public AdListener getMeishuAdListener(){
        return this.meishuAdListener;
    }

    public NativeAdSlot getAdSlot() {
        return adSlot;
    }
}
