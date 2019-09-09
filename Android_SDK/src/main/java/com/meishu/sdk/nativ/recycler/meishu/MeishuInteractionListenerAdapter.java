package com.meishu.sdk.nativ.recycler.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.meishu_ad.nativ.NativeAdInteractionListener;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.service.ClickHandler;

public class MeishuInteractionListenerAdapter implements NativeAdInteractionListener {
    private AdInteractionListener adInteractionListener;
    private NativeAdData nativeAdData;

    public MeishuInteractionListenerAdapter(@NonNull NativeAdData nativeAdData, AdInteractionListener adInteractionListener) {
        this.nativeAdData = nativeAdData;
        this.adInteractionListener = adInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if (this.adInteractionListener != null) {
            this.adInteractionListener.onAdClicked();
        }
        ClickHandler.handleClick(nativeAdData);
    }

    @Override
    public void onADExposure() {
        if (this.adInteractionListener != null) {
            this.adInteractionListener.onAdClicked();
        }
    }
}
