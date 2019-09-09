package com.meishu.sdk.meishu_ad.nativ;


import java.util.List;

public interface AdListener {

    void onAdLoaded(List<NativeAdData> adDatas);

    void onADExposure();

    void onAdError();
}
