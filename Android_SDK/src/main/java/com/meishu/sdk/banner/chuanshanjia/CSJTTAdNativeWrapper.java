package com.meishu.sdk.banner.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.banner.AdListenerWrapper;
import com.meishu.sdk.banner.BannerAdListener;
import com.meishu.sdk.banner.BannerAdDelegate;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper implements DelegateChain, BannerAdDelegate {
    private TTAdNative ttAdNative;
    private String posId;
    private TTAdNative.BannerAdListener bannerAdListener;
    private DelegateChain next;
    private SdkAdInfo sdkAdInfo;
    private Activity activity;

    public CSJTTAdNativeWrapper(@NonNull Activity context, @NonNull SdkAdInfo sdkAdInfo, BannerAdListener apiAdListener) {
        this.activity = context;
        this.sdkAdInfo = sdkAdInfo;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(context);
        this.posId = sdkAdInfo.getPid();
        this.bannerAdListener = new CSJBannerListenerImpl(this, new AdListenerWrapper(this, apiAdListener));
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.posId) //广告位id
                .setSupportDeepLink(true)
                .setImageAcceptedSize(600, 257)
                .build();
        ttAdNative.loadBannerAd(adSlot, this.bannerAdListener);
    }

    @Override
    public void destroy() {
        //do nothing
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
