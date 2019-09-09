package com.meishu.sdk.nativ.media;

public interface NativeAdMediaListener {

    void onVideoReady(long var1);

    void onVideoStart();

    void onVideoPause();

    void onVideoComplete();

    void onReplayButtonClicked();

    void onADButtonClicked();

    void onFullScreenChanged(boolean isFullScreen);
}
