package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.nativ.recycler.AdListenerWrapper;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.nativ.recycler.NativeAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper extends BaseSdkAdWrapper {

    private TTAdNative ttAdNative;
    private NativeAdLoader adLoader;

    public CSJTTAdNativeWrapper(@NonNull NativeAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(adLoader.getActivity());
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //feed广告请求类型参数
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.getSdkAdInfo().getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(640, 320)
                .setAdCount(1)
                .build();
        this.ttAdNative.loadFeedAd(adSlot, new CSJFeedAdListener(this, new AdListenerWrapper(this, this.adLoader.getApiAdListener())));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public NativeAdListener getAdListener() {
        return adLoader.getApiAdListener();
    }

}
