package com.meishu.sdk.interstitial.meishu;

import android.text.TextUtils;
import android.view.View;

import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.meishu_ad.interstitial.AdListener;
import com.meishu.sdk.meishu_ad.interstitial.NativeInterstitialAd;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class MeishuAdListenerAdapter implements AdListener {
    private InterstitialAdListener meishuAdListener;
    private MeishuAdNativeWrapper adWrapper;
    private volatile boolean hasExposed;

    public MeishuAdListenerAdapter(MeishuAdNativeWrapper adWrapper, InterstitialAdListener meishuAdListener) {
        this.adWrapper=adWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onLoaded(NativeInterstitialAd nativeAd) {
        if (meishuAdListener != null) {
            MeishuInterstitialAdAdapter meishuInterstitialAd = new MeishuInterstitialAdAdapter(nativeAd,this.meishuAdListener);
            View adView = nativeAd.getAdView();

            meishuInterstitialAd.setAdView(adView);
            meishuAdListener.onAdLoaded(meishuInterstitialAd);
        }
    }

    @Override
    public void onADExposure() {
        if(!hasExposed){//每个广告只曝光一次
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
            hasExposed=true;
        }

    }

    @Override
    public void onADClosed() {
        if (meishuAdListener != null) {
            meishuAdListener.onAdClosed();
        }
    }
}
