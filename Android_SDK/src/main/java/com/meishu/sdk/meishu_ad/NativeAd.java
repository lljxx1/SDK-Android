package com.meishu.sdk.meishu_ad;

import android.view.View;

import com.meishu.sdk.service.AdSlot;

public interface NativeAd {
    AdSlot getAdSlot();

    View getAdView();

    int getInteractionType();
}
