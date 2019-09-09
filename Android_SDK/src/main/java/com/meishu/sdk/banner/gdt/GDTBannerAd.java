package com.meishu.sdk.banner.gdt;

import android.view.View;

import com.meishu.sdk.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;

public class GDTBannerAd implements BannerAd {

    private View bannerView;
    private BannerInteractionListener interactionListener;

    public GDTBannerAd(View bannerView) {
        this.bannerView = bannerView;
    }

    @Override
    public View getAdView() {
        return bannerView;
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
