package com.meishu.sdk.splash.gdt;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.AdListenerWrapper;
import com.meishu.sdk.splash.SplashAdDelegate;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.splash.SplashAD;

public class GDTSplashAdWrapper implements SplashAdDelegate, DelegateChain {
    private static final String TAG = "GDTSplashAdWrapper";
    private SplashAD splashAD;
    private ViewGroup adContainer;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;
    private Activity activity;

    public GDTSplashAdWrapper(Activity context, ViewGroup adContainer, SdkAdInfo sdkAdInfo, SplashAdListener adListener, int fetchDelay) {
        this.adContainer = adContainer;
        this.sdkAdInfo = sdkAdInfo;
        this.activity = context;
        splashAD = new SplashAD(context, sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new GDTSplashADListenerImpl(this, new AdListenerWrapper(this, adListener)), fetchDelay);
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.adContainer != null) {
            splashAD.fetchAndShowIn(adContainer);
        } else {
            Log.e(TAG, "loadAd: ", new Exception("广告容器不能为空"));
        }
    }

    public View getView() {
        return this.adContainer;
    }

    @Override
    public void setNext(DelegateChain next) {
        this.next = next;
    }

    @Override
    public DelegateChain getNext() {
        return this.next;
    }

    @Override
    public SdkAdInfo getSdkAdInfo() {
        return this.sdkAdInfo;
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }
}