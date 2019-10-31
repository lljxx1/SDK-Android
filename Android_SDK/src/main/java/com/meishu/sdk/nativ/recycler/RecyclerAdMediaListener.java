package com.meishu.sdk.nativ.recycler;

public interface RecyclerAdMediaListener {

    void onVideoLoaded();

    void onVideoStart();

    void onVideoPause();

    void onVideoCompleted();

    void onVideoError();
}
