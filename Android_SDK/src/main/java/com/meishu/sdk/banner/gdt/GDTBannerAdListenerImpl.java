package com.meishu.sdk.banner.gdt;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
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
            View  originalView = bannerViewWrapper.getAdView();
            if (originalView != null && originalView.getParent() != null) {//广点通广告会滚动刷新内容，滚动后originalView.getParent的值为之前附加上的TouchAdContainer
                ViewGroup parent = (ViewGroup) originalView.getParent();
                parent.removeView(originalView);
            }

            this.bannerAd = new GDTBannerAd();

            TouchAdContainer touchContainer = new TouchAdContainer(originalView.getContext());
            touchContainer.setTouchPositionListener(new TouchPositionListener(bannerAd));
            touchContainer.addView(originalView);
            bannerAd.setAdView(touchContainer);

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
