package com.meishu.sdk.interstitial.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.AdListenerWrapper;
import com.meishu.sdk.interstitial.InterstitialAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper extends BaseSdkAdWrapper {
    private TTAdNative ttAdNative;
    private DelegateChain next;
    private InterstitialAdLoader adLoader;

    public CSJTTAdNativeWrapper(@NonNull InterstitialAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(adLoader.getActivity());
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        //step4:创建插屏广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.getSdkAdInfo().getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(600, 600) //根据广告平台选择的尺寸，传入同比例尺寸
                .build();
        ttAdNative.loadInteractionAd(adSlot, new CSJInteractionAdListenerImpl(this, new AdListenerWrapper(this, this.adLoader.getApiAdListener())));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

}
