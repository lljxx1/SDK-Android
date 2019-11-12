package com.meishu.sdk.nativ.image;

import android.app.Activity;

import com.meishu.sdk.nativ.image.meishu.MeishuRecyclerAdListenerAdapter;
import com.meishu.sdk.nativ.recycler.RecyclerAdLoader;

public class ImageAdLoader {

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

    private RecyclerAdLoader recyclerAdLoader;

    public ImageAdLoader(Activity context, String posId, NativeAdListener listener) {
        this.recyclerAdLoader = new RecyclerAdLoader(context, posId, 1,new MeishuRecyclerAdListenerAdapter(listener));
    }

    public void loadData() {
        this.recyclerAdLoader.loadAd();
    }

}
