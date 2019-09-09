package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.nativ.recycler.AdListenerWrapper;
import com.meishu.sdk.nativ.recycler.NativeAdDelegate;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper implements NativeAdDelegate, DelegateChain {

    private TTAdNative ttAdNative;
    private NativeAdListener adListener;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;
    private Activity activity;

    public CSJTTAdNativeWrapper(@NonNull Activity activity, @NonNull SdkAdInfo sdkAdInfo, @NonNull NativeAdListener adListener) {
        this.activity = activity;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(activity);
        this.sdkAdInfo = sdkAdInfo;
        this.adListener = adListener;
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //feed广告请求类型参数
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(sdkAdInfo.getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(640, 320)
                .setAdCount(1)
                .build();
        this.ttAdNative.loadFeedAd(adSlot, new CSJFeedAdListener(this, new AdListenerWrapper(this, this.adListener)));
    }

    public NativeAdListener getAdListener() {
        return adListener;
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
