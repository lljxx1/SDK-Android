package com.meishu.sdk.splash.chuanshanjia;

import android.view.View;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.splash.SplashInteractionListener;

public class CSJSplashAd extends BaseAdData implements SplashAd {
    private TTSplashAd ttSplashAd;
    private SplashAdListener apiAdListener;
    private SplashInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;
    private View adView;

    public CSJSplashAd(SdkAdInfo sdkAdInfo, TTSplashAd ttSplashAd, SplashAdListener apiAdListener) {
        this.sdkAdInfo = sdkAdInfo;
        this.ttSplashAd = ttSplashAd;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    public void setAdView(View adView) {
        this.adView = adView;
    }

    @Override
    public void setInteractionListener(SplashInteractionListener listener) {
        this.interactionListener = listener;
        if (ttSplashAd != null && listener != null) {
            ttSplashAd.setSplashInteractionListener(new CSJSplashInteractionListenerImpl(this, listener));
        }
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }


    public SplashAdListener getApiAdListener() {
        return apiAdListener;
    }

    public SdkAdInfo getSdkAdInfo() {
        return this.sdkAdInfo;
    }

    public int getInteractionType() {
        int interactionType = 0;
        if (this.ttSplashAd.getInteractionType() == TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
            interactionType = MeishuConstants.interactionType_download;
        } else if (this.ttSplashAd.getInteractionType() == TTAdConstant.INTERACTION_TYPE_LANDING_PAGE) {
            interactionType = MeishuConstants.interactionType_url;
        } else {

        }
        return interactionType;
    }
}
