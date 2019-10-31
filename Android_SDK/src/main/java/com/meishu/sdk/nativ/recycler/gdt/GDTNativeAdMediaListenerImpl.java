package com.meishu.sdk.nativ.recycler.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.recycler.RecyclerAdMediaListener;
import com.qq.e.ads.nativ.NativeADMediaListener;
import com.qq.e.comm.util.AdError;

public class GDTNativeAdMediaListenerImpl implements NativeADMediaListener {
    private static final String TAG = "GDTNativeAdMediaListene";
    private RecyclerAdMediaListener nativeRecyclerAdMediaListener;

    public GDTNativeAdMediaListenerImpl(@NonNull RecyclerAdMediaListener nativeRecyclerAdMediaListener) {
        this.nativeRecyclerAdMediaListener = nativeRecyclerAdMediaListener;
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
        this.nativeRecyclerAdMediaListener.onVideoLoaded();
    }

    @Override
    public void onVideoStart() {
        this.nativeRecyclerAdMediaListener.onVideoStart();
    }

    @Override
    public void onVideoPause() {
        this.nativeRecyclerAdMediaListener.onVideoPause();
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
        this.nativeRecyclerAdMediaListener.onVideoError();
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
