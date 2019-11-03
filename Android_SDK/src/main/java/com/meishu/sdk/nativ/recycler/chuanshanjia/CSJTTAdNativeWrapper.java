package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.nativ.recycler.SdkAdListenerWrapper;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;
import com.meishu.sdk.nativ.recycler.RecyclerAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper extends BaseSdkAdWrapper {

    private TTAdNative ttAdNative;
    private RecyclerAdLoader adLoader;
    private MeishuAdInfo meishuAdInfo;

    public CSJTTAdNativeWrapper(@NonNull RecyclerAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo, @NonNull MeishuAdInfo meishuAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(adLoader.getActivity());
        this.meishuAdInfo = meishuAdInfo;
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //feed广告请求类型参数
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.getSdkAdInfo().getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(meishuAdInfo.getWidth(), meishuAdInfo.getHeight())
                .setAdCount(1)
                .build();
        this.ttAdNative.loadFeedAd(adSlot, new CSJFeedAdListener(this, new SdkAdListenerWrapper(this, this.adLoader.getApiAdListener())));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public RecyclerAdListener getAdListener() {
        return adLoader.getApiAdListener();
    }

}
