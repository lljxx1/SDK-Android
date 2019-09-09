package com.meishu.sdk.meishu_ad.banner;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.meishu_ad.NativeDownloadListener;

public class BannerAdImpl implements BannerAd {
    private View adView;
    private InteractionListener interactionListener;
    private NativeDownloadListener downloadListener;
    private BannerAdSlot adSlot;

    public BannerAdImpl(@NonNull BannerAdSlot adSlot) {
        this.adSlot = adSlot;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    @Override
    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @Override
    public void setDownloadListener(NativeDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public int getInteractionType() {
        return this.adSlot.getInteractionType();
    }

    public InteractionListener getInteractionListener() {
        return interactionListener;
    }

    public void setAdView(View adView) {
        this.adView = adView;
    }

    public BannerAdSlot getAdSlot() {
        return adSlot;
    }
}
