package com.meishu.sdk.interstitial.meishu;

import android.text.TextUtils;

import com.meishu.sdk.banner.meishu.MeishuBannerViewWrapper;
import com.meishu.sdk.meishu_ad.interstitial.AdListener;
import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class MeishuAdListenerAdapter implements AdListener {
    private InterstitialAdListener meishuAdListener;
    private MeishuAdNativeWrapper adWrapper;

    public MeishuAdListenerAdapter(MeishuAdNativeWrapper adWrapper, InterstitialAdListener meishuAdListener) {
        this.adWrapper=adWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onLoaded(NativeInterstitialAd nativeAd) {
        if (meishuAdListener != null) {
            meishuAdListener.onAdLoaded(new MeishuInterstitialAdAdapter(nativeAd,this.meishuAdListener));
        }
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
        if (meishuAdListener != null) {
            meishuAdListener.onAdExposure();
        }
    }

    @Override
    public void onADClosed() {
        if (meishuAdListener != null) {
            meishuAdListener.onAdClosed();
        }
    }
}
