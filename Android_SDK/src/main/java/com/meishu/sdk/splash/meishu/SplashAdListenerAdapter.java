package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.meishu.sdk.meishu_ad.splash.AdListener;
import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class SplashAdListenerAdapter implements AdListener {
    private com.meishu.sdk.splash.SplashAdListener splashAdListener;
    private MeishuAdNativeWrapper adWrapper;

    public SplashAdListenerAdapter(MeishuAdNativeWrapper adWrapper, @NonNull SplashAdListener splashAdListener) {
        this.adWrapper = adWrapper;
        this.splashAdListener = splashAdListener;
    }

    @Override
    public void onLoaded(NativeSplashAd splashAd) {
        splashAdListener.onLoaded(new MeishuSplashAdAdapter(splashAd));
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
        splashAdListener.onAdExposure();
    }

    @Override
    public void onADClosed() {
        splashAdListener.onAdClosed();
    }
}
