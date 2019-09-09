package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.domain.SdkAdInfo;

public class CSJBannerAd implements BannerAd {
    private TTBannerAd ttBannerAd;
    private BannerInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;

    public CSJBannerAd(@NonNull SdkAdInfo sdkAdInfo, TTBannerAd ttBannerAd) {
        this.sdkAdInfo=sdkAdInfo;
        this.ttBannerAd = ttBannerAd;
    }

    @Override
    public View getAdView() {
        if (this.ttBannerAd != null) {
            return this.ttBannerAd.getBannerView();
        } else {
            return null;
        }
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
        if (this.ttBannerAd != null) {
            this.ttBannerAd.setBannerInteractionListener(new CSJBannerInteractionListenerImpl(this,listener));
        }
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    public SdkAdInfo getSdkAdInfo() {
        return sdkAdInfo;
    }
}
