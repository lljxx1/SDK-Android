package com.meishu.sdk.nativ.image;

import java.util.List;

public interface NativeAdListener {

    void onAdLoaded(List<NativeAdData> adDatas);

    void onAdExposure();

//    void onAdClosed();

    void onAdError();
}
