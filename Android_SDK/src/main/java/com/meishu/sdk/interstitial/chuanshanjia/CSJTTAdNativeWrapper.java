package com.meishu.sdk.interstitial.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.AdListenerWrapper;
import com.meishu.sdk.interstitial.InterstitialAdDelegate;
import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper implements InterstitialAdDelegate, DelegateChain {
    private TTAdNative ttAdNative;
    private InterstitialAdListener meishuAdListener;
    private Activity activity;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;

    public CSJTTAdNativeWrapper(@NonNull Activity activity, SdkAdInfo sdkAdInfo, InterstitialAdListener meishuAdListener) {
        this.activity = activity;
        this.sdkAdInfo = sdkAdInfo;
        this.meishuAdListener = meishuAdListener;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(activity);
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //step4:创建插屏广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.sdkAdInfo.getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(600, 600) //根据广告平台选择的尺寸，传入同比例尺寸
                .build();
        ttAdNative.loadInteractionAd(adSlot, new CSJInteractionAdListenerImpl(this, new AdListenerWrapper(this, meishuAdListener)));
    }

    @Override
    public void destroy() {
        //do nothing
    }

    @Override
    public Activity getActivity() {
        return this.activity;
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
}
