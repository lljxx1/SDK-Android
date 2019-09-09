package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.meishu_ad.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;

public class BannerAdAdapter implements com.meishu.sdk.banner.BannerAd {
    private BannerAd nativeBannerAd;
    private BannerInteractionListener interactionListener;


    public BannerAdAdapter(@NonNull BannerAd nativeBannerAd) {
        this.nativeBannerAd = nativeBannerAd;
    }

    @Override
    public View getAdView() {
        return this.nativeBannerAd.getAdView();
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
        this.nativeBannerAd.setInteractionListener(new MeishuBannerInteractionListener(this.nativeBannerAd, listener));
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
