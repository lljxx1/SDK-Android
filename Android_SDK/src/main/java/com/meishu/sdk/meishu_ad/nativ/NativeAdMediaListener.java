package com.meishu.sdk.meishu_ad.nativ;

public interface NativeAdMediaListener {

    void onVideoLoaded();

    void onVideoStart();

    void onVideoOneQuarter();
    void onVideoOneHalf();
    void onVideoThreeQuarter();
    void onVideoComplete();

    void onVideoPause();

//    void onVideoResume();

    void onVideoMute();
    void onVideoUnmute();
    void onVideoReplay();

    void onVideoError();
}
