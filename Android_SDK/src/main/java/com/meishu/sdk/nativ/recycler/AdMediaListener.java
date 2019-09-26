package com.meishu.sdk.nativ.recycler;

public interface AdMediaListener {

    void onVideoLoaded();

    void onVideoStart();

    void onVideoPause();

    void onVideoCompleted();

    void onVideoError();
}
