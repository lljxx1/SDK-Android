package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.meishu.sdk.meishu_ad.banner.AdListener;
import com.meishu.sdk.meishu_ad.banner.BannerAd;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class BannerAdListenerAdapter implements AdListener {
    private com.meishu.sdk.banner.BannerAdListener bannerAdListener;
    private MeishuViewWrapper adWrapper;

    public BannerAdListenerAdapter(@NonNull MeishuViewWrapper adWrapper, @NonNull com.meishu.sdk.banner.BannerAdListener bannerAdListener) {
        this.adWrapper = adWrapper;
        this.bannerAdListener = bannerAdListener;
    }

    @Override
    public void onLoaded(BannerAd bannerAd) {
        bannerAdListener.onLoaded(new MeishuBannerAdAdapter(bannerAd));
    }

    @Override
    public void onADExposure() {
        String[] monitorUrls = this.adWrapper.getAdSlot().getMonitorUrl();
        if (monitorUrls != null) {
            for (String monitorUrl : monitorUrls) {
                if (!TextUtils.isEmpty(monitorUrl)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), monitorUrl, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        bannerAdListener.onAdExposure();
    }

    @Override
    public void onADClosed() {
        bannerAdListener.onAdClosed();
    }
}
