package com.meishu.sdk.nativ.recycler;

import java.util.List;

public interface NativeAdListener {

    void onAdLoaded(List<AdData> adDatas);

    void onAdExposure();

    void onAdClosed();

    void onAdError();
}
