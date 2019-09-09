package com.meishu.sdk.nativ.image.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.nativ.image.NativeAdDelegate;
import com.meishu.sdk.nativ.image.NativeAdListener;

public class CSJTTAdNativeWrapper implements NativeAdDelegate {

    private TTAdNative ttAdNative;
    private String posId;
    private NativeAdListener adListener;

    public CSJTTAdNativeWrapper(@NonNull Activity activity, @NonNull String posId, @NonNull NativeAdListener adListener) {
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(activity);
        this.posId = posId;
        this.adListener = adListener;
    }

    @Override
    public void loadData() {
        //step4:创建广告请求参数AdSlot,注意其中的setNativeAdtype方法，具体参数含义参考文档
        final AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.posId)
                .setSupportDeepLink(true)
                .setImageAcceptedSize(600, 257)
                .setNativeAdType(AdSlot.TYPE_BANNER) //请求原生广告时候，请务必调用该方法，设置参数为TYPE_BANNER或TYPE_INTERACTION_AD
                .setAdCount(1)
                .build();
        ttAdNative.loadNativeAd(adSlot, new CSJNativeAdListener(this, this.adListener));
    }
}
