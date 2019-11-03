package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
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
        MeishuBannerAdAdapter meishuBannerAd= new MeishuBannerAdAdapter(bannerAd);

        View adView=bannerAd.getAdView();

        TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(meishuBannerAd));
        touchContainer.addView(adView);
        adView= touchContainer;

        meishuBannerAd.setAdView(adView);

        bannerAdListener.onLoaded(meishuBannerAd);
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
