package com.meishu.sdk.banner.gdt;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.meishu.sdk.banner.BannerAdListener;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.banner.BannerADListener;
import com.qq.e.comm.util.AdError;

public class GDTBannerAdListenerImpl implements BannerADListener {
    private static final String TAG = "GDTBannerAdListenerImpl";
    private BannerAdListener meishuBannerAdListener;

    private GDTViewWrapper bannerViewWrapper;

    public GDTBannerAdListenerImpl(@NonNull GDTViewWrapper bannerViewWrapper, BannerAdListener meishuBannerAdListener) {
        this.bannerViewWrapper = bannerViewWrapper;
        this.meishuBannerAdListener = meishuBannerAdListener;
    }

    private GDTBannerAd bannerAd;

    @Override
    public void onADClicked() {
        if (bannerViewWrapper.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    bannerViewWrapper.getAdView().getContext(),
                    ClickHandler.replaceOtherMacros(
                            bannerViewWrapper.getSdkAdInfo().getClk(),
                            this.bannerAd
                    ),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (this.bannerAd != null && this.bannerAd.getInteractionListener() != null) {
            this.bannerAd.getInteractionListener().onAdClicked();
        }
    }

    @Override
    public void onADExposure() {
        if (meishuBannerAdListener != null) {
            meishuBannerAdListener.onAdExposure();
        }
    }

    @Override
    public void onADClosed() {
        if (meishuBannerAdListener != null) {
            meishuBannerAdListener.onAdClosed();
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        if (this.meishuBannerAdListener != null) {
            this.meishuBannerAdListener.onError();
        }
    }

    @Override
    public void onADReceiv() {
        if (meishuBannerAdListener != null) {
            this.bannerAd = new GDTBannerAd(bannerViewWrapper.getAdView());
            if (bannerAd.getAdView() != null && bannerAd.getAdView().getParent() != null) {
                ViewGroup parent = (ViewGroup) bannerAd.getAdView().getParent();
                parent.removeView(bannerAd.getAdView());
            }
            meishuBannerAdListener.onLoaded(this.bannerAd);
        }
    }

    @Override
    public void onADLeftApplication() {
        Log.d(TAG, "onADLeftApplication: ");
    }

    @Override
    public void onADOpenOverlay() {
        Log.d(TAG, "onADOpenOverlay: ");
    }

    @Override
    public void onADCloseOverlay() {
        Log.d(TAG, "onADCloseOverlay: ");
    }
}
