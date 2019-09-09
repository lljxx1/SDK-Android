package com.meishu.sdk.nativ.media;

import java.util.List;

public interface NativeMediaAdListener {

    void onAdLoaded(List<NativeMediaAdData> mediaAdDatas);

    void onVideoLoaded(NativeMediaAdData mediaAdData);

    void onAdClicked(NativeMediaAdData mediaAdData);

    void onADExposure(NativeMediaAdData mediaAdData);

}
