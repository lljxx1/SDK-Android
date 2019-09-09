package com.meishu.sdk.splash.chuanshanjia;

import android.app.Activity;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.AdListenerWrapper;
import com.meishu.sdk.splash.SplashAdDelegate;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper implements SplashAdDelegate, DelegateChain {

    private TTAdNative ttAdNative;
    private ViewGroup adContainer;
    private TTAdNative.SplashAdListener ttAdListener;
    private int fetchDelay;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;
    private Activity activity;

    public CSJTTAdNativeWrapper(Activity context, ViewGroup adContainer, SdkAdInfo sdkAdInfo, SplashAdListener adListener, int fetchDelay) {
        this.activity = context;
        this.adContainer = adContainer;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(context);
        this.sdkAdInfo = sdkAdInfo;
        this.fetchDelay = fetchDelay;
        this.ttAdListener = new TTSplashAdListenerImpl(this, new AdListenerWrapper(this, adListener));
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
//step4:创建广告请求参数AdSlot, 具体参数含义参考文档
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.sdkAdInfo.getPid()) //广告位id
                .setSupportDeepLink(true)
                .setImageAcceptedSize(1080, 1920)
                .build();
        ttAdNative.loadSplashAd(adSlot, this.ttAdListener, this.fetchDelay);
    }

    public ViewGroup getView() {
        return adContainer;
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
