package com.meishu.sdk.nativ.recycler.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.nativ.recycler.NativeAdLoader;

public class MeishuAdNativeWrapper extends BaseMeishuWrapper {
    private AdNative adNative;
    private NativeAdSlot adSlot;
    private NativeAdLoader adLoader;

    public MeishuAdNativeWrapper(@NonNull NativeAdLoader adLoader, NativeAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader = adLoader;
        this.adNative = new AdNative(adLoader.getActivity());
        this.adSlot = adSlot;
    }

    @Override
    public void loadAd() {
        adNative.loadNativeAd(this.adSlot, new MeishuAdListenerAdapter(this, this.adLoader.getApiAdListener()));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public NativeAdSlot getAdSlot() {
        return adSlot;
    }
}
