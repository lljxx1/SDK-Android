package com.meishu.sdk.nativ.image;

import android.app.Activity;

import com.meishu.sdk.nativ.image.meishu.MeishuNativeAdListenerAdapter;

public class NativeAdLoader {

//    private NativeAdDelegate nativeADDelegate;
//
//    public NativeAdLoader(Activity context, String appId, String posId, NativeAdListener listener) {
//        this.nativeADDelegate = new GDTNativeAdWrapper(context, appId, posId, listener);
////        this.nativeADDelegate = new CSJTTAdNativeWrapper(context, posId, listener);
//    }

//    @Override
//    public void loadData() {
//        this.nativeADDelegate.loadData();
//    }

    private com.meishu.sdk.nativ.recycler.NativeAdLoader nativeAdLoader;

    public NativeAdLoader(Activity context, String posId, NativeAdListener listener) {
        this.nativeAdLoader = new com.meishu.sdk.nativ.recycler.NativeAdLoader(context, posId, new MeishuNativeAdListenerAdapter(listener));
    }

    public void loadData() {
        this.nativeAdLoader.loadAd();
    }

}
