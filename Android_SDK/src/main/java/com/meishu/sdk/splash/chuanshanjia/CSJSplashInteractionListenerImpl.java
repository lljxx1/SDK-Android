package com.meishu.sdk.splash.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.splash.SplashInteractionListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJSplashInteractionListenerImpl implements TTSplashAd.AdInteractionListener {
    private static final String TAG = "CSJSplashInteractionLis";
    private SplashInteractionListener meishuInteractionListener;
    private CSJSplashAd csjSplashAd;

    public CSJSplashInteractionListenerImpl(@NonNull CSJSplashAd csjSplashAd, SplashInteractionListener meishuInteractionListener) {
        this.csjSplashAd = csjSplashAd;
        this.meishuInteractionListener = meishuInteractionListener;
    }

    @Override
    public void onAdClicked(View view, int i) {
        if (this.csjSplashAd.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    csjSplashAd.getAdView().getContext(),
                    ClickHandler.replaceOtherMacros(this.csjSplashAd.getSdkAdInfo().getClk(), this.csjSplashAd),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (meishuInteractionListener != null) {
            meishuInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onAdShow(View view, int i) {
        if (csjSplashAd.getApiAdListener() != null) {
            csjSplashAd.getApiAdListener().onAdExposure();
        }
    }

    @Override
    public void onAdSkip() {
        if (csjSplashAd.getApiAdListener() != null) {
            csjSplashAd.getApiAdListener().onAdClosed();
        }
    }

    @Override
    public void onAdTimeOver() {
        if (csjSplashAd.getApiAdListener() != null) {
            csjSplashAd.getApiAdListener().onAdClosed();
        }
    }
}
