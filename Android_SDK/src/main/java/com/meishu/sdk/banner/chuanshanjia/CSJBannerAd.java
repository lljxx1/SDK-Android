package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.domain.SdkAdInfo;

public class CSJBannerAd extends BaseAdData implements BannerAd {
    private TTBannerAd ttBannerAd;
    private BannerInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;
    private View adView;

    public CSJBannerAd(@NonNull SdkAdInfo sdkAdInfo, TTBannerAd ttBannerAd) {
        this.sdkAdInfo=sdkAdInfo;
        this.ttBannerAd = ttBannerAd;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    public void setAdView(View adView) {
        this.adView = adView;
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
