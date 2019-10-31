package com.meishu.sdk.nativ.recycler;

import java.util.List;

public interface RecyclerAdListener {

    void onAdLoaded(List<RecyclerAdData> adDatas);

    void onAdExposure();

    void onAdClosed();

    void onAdError();
}
