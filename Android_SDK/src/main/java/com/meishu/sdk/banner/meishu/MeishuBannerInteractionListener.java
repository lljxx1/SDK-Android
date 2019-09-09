package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.meishu_ad.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.service.ClickHandler;

public class MeishuBannerInteractionListener implements BannerAd.InteractionListener {
    private static final String TAG = "MeishuBannerInteraction";
    private BannerInteractionListener interactionListener;
    private BannerAd nativeAd;

    public MeishuBannerInteractionListener(@NonNull BannerAd nativeBannerAd, BannerInteractionListener interactionListener) {
        this.nativeAd = nativeBannerAd;
        this.interactionListener = interactionListener;
    }

    @Override
    public void onAdClicked() {
        if (interactionListener != null) {
            interactionListener.onAdClicked();
        }
        ClickHandler.handleClick(nativeAd);
    }
}
