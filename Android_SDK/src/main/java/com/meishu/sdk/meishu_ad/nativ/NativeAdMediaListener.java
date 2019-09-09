package com.meishu.sdk.meishu_ad.nativ;

public interface NativeAdMediaListener {

    void onVideoLoaded();

    void onVideoStart();

    void onVideoPause();

    void onVideoResume();

    void onVideoError();
}
