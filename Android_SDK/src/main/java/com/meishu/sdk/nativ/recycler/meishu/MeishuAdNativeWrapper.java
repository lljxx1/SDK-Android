package com.meishu.sdk.nativ.recycler.meishu;

import android.app.Activity;

import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.nativ.recycler.NativeAdDelegate;
import com.meishu.sdk.nativ.recycler.NativeAdListener;

public class MeishuAdNativeWrapper implements NativeAdDelegate {
    private AdNative adNative;
    private NativeAdSlot adSlot;
    private NativeAdListener apiAdListener;

    public MeishuAdNativeWrapper(Activity activity, NativeAdSlot adSlot, NativeAdListener apiAdListener) {
        this.adNative = new AdNative(activity);
        this.adSlot = adSlot;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void loadAd() {
        adNative.loadNativeAd(this.adSlot, new MeishuAdListenerAdapter(apiAdListener));
    }
}
