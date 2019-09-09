package com.meishu.sdk.meishu_ad.interstitial;

import android.view.View;

import com.meishu.sdk.meishu_ad.NativeAd;
import com.meishu.sdk.meishu_ad.NativeDownloadListener;
import com.meishu.sdk.service.AdSlot;

public interface NativeInterstitialAd extends NativeAd {

    View getAdView();

    void setInteractionListener(InteractionListener interactionListener);

    void setDownloadListener(NativeDownloadListener downloadListener);

    int getInteractionType();

    AdSlot getAdSlot();

    public interface InteractionListener {
        void onAdClicked();
    }
}
