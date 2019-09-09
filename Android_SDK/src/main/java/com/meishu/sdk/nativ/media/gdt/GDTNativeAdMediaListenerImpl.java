package com.meishu.sdk.nativ.media.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.media.NativeAdMediaListener;
import com.qq.e.ads.nativ.MediaListener;
import com.qq.e.comm.util.AdError;

public class GDTNativeAdMediaListenerImpl implements MediaListener {
    private static final String TAG = "GDTNativeAdMediaListene";
    private NativeAdMediaListener nativeAdMediaListener;

    public GDTNativeAdMediaListenerImpl(@NonNull NativeAdMediaListener nativeAdMediaListener) {
        this.nativeAdMediaListener = nativeAdMediaListener;
    }


    @Override
    public void onVideoReady(long l) {
        nativeAdMediaListener.onVideoReady(l);
    }

    @Override
    public void onVideoStart() {
        nativeAdMediaListener.onVideoStart();
    }

    @Override
    public void onVideoPause() {
        nativeAdMediaListener.onVideoPause();
    }

    @Override
    public void onVideoComplete() {
        nativeAdMediaListener.onVideoComplete();
    }

    @Override
    public void onVideoError(AdError adError) {
        Log.d(TAG, "onVideoError: ");
    }

    @Override
    public void onReplayButtonClicked() {
        nativeAdMediaListener.onReplayButtonClicked();
    }

    @Override
    public void onADButtonClicked() {
        nativeAdMediaListener.onADButtonClicked();
    }

    @Override
    public void onFullScreenChanged(boolean isFullScreen) {
        nativeAdMediaListener.onFullScreenChanged(isFullScreen);
    }
}
