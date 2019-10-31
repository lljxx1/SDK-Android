package com.meishu.sdk.nativ.recycler.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.meishu_ad.nativ.NativeAdInteractionListener;
import com.meishu.sdk.nativ.recycler.RecylcerAdInteractionListener;
import com.meishu.sdk.service.ClickHandler;

public class MeishuInteractionListenerAdapter implements NativeAdInteractionListener {
    private RecylcerAdInteractionListener recylcerAdInteractionListener;
    private NativeAdData nativeAdData;

    public MeishuInteractionListenerAdapter(@NonNull NativeAdData nativeAdData, RecylcerAdInteractionListener recylcerAdInteractionListener) {
        this.nativeAdData = nativeAdData;
        this.recylcerAdInteractionListener = recylcerAdInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if (this.recylcerAdInteractionListener != null) {
            this.recylcerAdInteractionListener.onAdClicked();
        }
        ClickHandler.handleClick(nativeAdData);
    }

    @Override
    public void onADExposure() {
        if (this.recylcerAdInteractionListener != null) {
            this.recylcerAdInteractionListener.onAdClicked();
        }
    }
}
