package com.meishu.sdk.splash.gdt;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.AdListenerWrapper;
import com.meishu.sdk.splash.SplashAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.splash.SplashAD;

public class GDTSplashAdWrapper extends BaseSdkAdWrapper {
    private static final String TAG = "GDTSplashAdWrapper";
    private SplashAD splashAD;
    private SplashAdLoader adLoader;

    public GDTSplashAdWrapper(@NonNull SplashAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        splashAD = new SplashAD(adLoader.getActivity(), sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new GDTSplashADListenerImpl(this, new AdListenerWrapper(this, adLoader.getApiAdListener())), adLoader.getFetchDelay());
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.adLoader.getAdContainer() != null) {
            splashAD.fetchAndShowIn(this.adLoader.getAdContainer());
        } else {
            Log.e(TAG, "loadAd: ", new Exception("广告容器不能为空"));
        }
    }

    @Override
    public AdLoader getAdLoader() {
        return adLoader;
    }

    public View getView() {
        return this.adLoader.getAdContainer();
    }
}