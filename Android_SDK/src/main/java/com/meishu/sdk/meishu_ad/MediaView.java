package com.meishu.sdk.meishu_ad;

import android.view.View;
import android.view.ViewParent;

import com.meishu.sdk.meishu_ad.nativ.NativeAdMediaListener;

public interface MediaView {

    void start();

    void setOnVideoLoadedListener(OnVideoLoadedListener onVideoLoadedListener);

    void addOnVideoCompleteListener(OnVideoCompleteListener onVideoCompleteListener);

    void setVideoPath(String videoPath);

    void performVideoActions();

    View getVideoView();
    ViewParent getParent();

    void setNativeAdMediaListener(NativeAdMediaListener nativeAdMediaListener);

    interface OnVideoLoadedListener {
        void onLoaded(MediaView mediaView);
    }

    interface OnVideoCompleteListener{
        void onCompleted();
    }
}
