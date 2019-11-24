package com.meishu.sdk.interstitial.chuanshanjia;

import android.support.annotation.NonNull;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.InterstitialAdLoader;
import com.meishu.sdk.interstitial.SdkAdListenerWrapper;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJTTAdNativeWrapper extends BaseSdkAdWrapper {
    private TTAdNative ttAdNative;
    private InterstitialAdLoader adLoader;
    private MeishuAdInfo meishuAdInfo;

    public CSJTTAdNativeWrapper(@NonNull InterstitialAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo, @NonNull MeishuAdInfo meishuAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        this.ttAdNative = TTAdSdk.getAdManager().createAdNative(adLoader.getActivity());
        this.meishuAdInfo = meishuAdInfo;
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        int adContentWidth=1080;
        int adContentHeight=1920;
        if(meishuAdInfo.getWidth()!=null&& meishuAdInfo.getHeight()!=null){
            adContentWidth=meishuAdInfo.getWidth();
            adContentHeight=meishuAdInfo.getHeight();
        }
        //step4:创建插屏广告请求参数AdSlot,具体参数含义参考文档
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.getSdkAdInfo().getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(adContentWidth, adContentHeight) //根据广告平台选择的尺寸，传入同比例尺寸
                .build();
        ttAdNative.loadInteractionAd(adSlot, new CSJInteractionAdListenerImpl(this, new SdkAdListenerWrapper(this, this.adLoader.getApiAdListener())));
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

}
