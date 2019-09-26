package com.meishu.sdk.nativ.recycler.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.recycler.AdMediaListener;
import com.qq.e.ads.nativ.NativeADMediaListener;
import com.qq.e.comm.util.AdError;

public class GDTNativeAdMediaListenerImpl implements NativeADMediaListener {
    private static final String TAG = "GDTNativeAdMediaListene";
    private AdMediaListener nativeAdMediaListener;

    public GDTNativeAdMediaListenerImpl(@NonNull AdMediaListener nativeAdMediaListener) {
        this.nativeAdMediaListener = nativeAdMediaListener;
    }

    @Override
    public void onVideoInit() {
        Log.d(TAG, "onVideoInit: ");
    }

    @Override
    public void onVideoLoading() {
        Log.d(TAG, "onVideoLoading: ");
    }

    @Override
    public void onVideoReady() {
        Log.d(TAG, "onVideoReady: ");
    }

    @Override
    public void onVideoLoaded(int i) {
        this.nativeAdMediaListener.onVideoLoaded();
    }

    @Override
    public void onVideoStart() {
        this.nativeAdMediaListener.onVideoStart();
    }

    @Override
    public void onVideoPause() {
        this.nativeAdMediaListener.onVideoPause();
    }

    @Override
    public void onVideoResume() {
        Log.d(TAG, "onVideoResume: ");
    }

    @Override
    public void onVideoCompleted() {
        Log.d(TAG, "onVideoCompleted: ");
    }

    @Override
    public void onVideoError(AdError adError) {
        this.nativeAdMediaListener.onVideoError();
    }

    @Override
    public void onVideoStop() {
        Log.d(TAG, "onVideoStop: ");
    }

    @Override
    public void onVideoClicked() {
        Log.d(TAG, "onVideoClicked: ");
    }
}
