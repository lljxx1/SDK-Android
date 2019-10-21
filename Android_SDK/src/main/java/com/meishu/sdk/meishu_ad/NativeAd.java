package com.meishu.sdk.meishu_ad;

import android.view.View;

import com.meishu.sdk.AdData;
import com.meishu.sdk.service.AdSlot;

public interface NativeAd extends AdData {
    AdSlot getAdSlot();

    View getAdView();

    int getInteractionType();
}
