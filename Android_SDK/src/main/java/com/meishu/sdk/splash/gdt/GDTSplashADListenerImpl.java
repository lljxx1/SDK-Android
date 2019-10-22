package com.meishu.sdk.splash.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.comm.util.AdError;

public class GDTSplashADListenerImpl implements com.qq.e.ads.splash.SplashADListener {
    private static final String TAG = "GDTSplashADListenerImpl";
    private SplashAdListener splashADListener;

    private GDTSplashAdWrapper splashAdWrapper;

    public GDTSplashADListenerImpl(@NonNull GDTSplashAdWrapper splashAdWrapper, SplashAdListener splashADListener) {
        this.splashAdWrapper = splashAdWrapper;
        this.splashADListener = splashADListener;
    }

    private GDTSplashAd splashAd;

    @Override
    public void onADClicked() {
        if (this.splashAdWrapper.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    this.splashAdWrapper.getView().getContext(),
                    ClickHandler.replaceOtherMacros(this.splashAdWrapper.getSdkAdInfo().getClk(),this.splashAd),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (splashAd != null && splashAd.getInteractionListener() != null) {
            splashAd.getInteractionListener().onAdClicked();
        }
    }

    @Override
    public void onADExposure() {
        if (splashADListener != null) {
            splashADListener.onAdExposure();
        }
    }

    @Override
    public void onADTick(long l) {
        Log.d(TAG, "onADTick: ");
    }

    @Override
    public void onADDismissed() {
        if (splashADListener != null) {
            splashADListener.onAdClosed();
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        if (this.splashADListener != null) {
            this.splashADListener.onError();
        }
    }

    @Override
    public void onADPresent() {
        if (splashAdWrapper != null && splashAdWrapper.getView() != null) {
            splashAd = new GDTSplashAd(splashAdWrapper.getView());
            splashADListener.onLoaded(splashAd);
        }
    }
}
