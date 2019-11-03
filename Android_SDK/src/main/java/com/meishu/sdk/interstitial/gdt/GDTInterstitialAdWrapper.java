package com.meishu.sdk.interstitial.gdt;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.SdkAdListenerWrapper;
import com.meishu.sdk.interstitial.InterstitialAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.interstitial.InterstitialAD;

public class GDTInterstitialAdWrapper extends BaseSdkAdWrapper {
    private InterstitialAD interstitialAD;
    private InterstitialAdLoader adLoader;

    public GDTInterstitialAdWrapper(@NonNull InterstitialAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        interstitialAD = new InterstitialAD(adLoader.getActivity(), sdkAdInfo.getApp_id(), sdkAdInfo.getPid());
        if (adLoader.getApiAdListener() != null) {
            interstitialAD.setADListener(new GDTInterstitialADListenerIml(this, new SdkAdListenerWrapper(this, adLoader.getApiAdListener())));
        }
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        interstitialAD.loadAD();
    }

    @Override
    public void destroy() {
        if (interstitialAD != null) {
            interstitialAD.destroy();
        }
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public void showAD() {
        interstitialAD.show();
    }

}
