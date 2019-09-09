package com.meishu.sdk.interstitial.gdt;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.AdListenerWrapper;
import com.meishu.sdk.interstitial.InterstitialAdDelegate;
import com.meishu.sdk.interstitial.InterstitialAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.interstitial.InterstitialAD;

public class GDTInterstitialAdWrapper implements InterstitialAdDelegate, DelegateChain {
    private InterstitialAD interstitialAD;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;
    private Activity activity;

    public GDTInterstitialAdWrapper(Activity context, SdkAdInfo sdkAdInfo, @NonNull InterstitialAdListener adListener) {
        this.sdkAdInfo = sdkAdInfo;
        this.activity = context;
        interstitialAD = new InterstitialAD(context, sdkAdInfo.getApp_id(), sdkAdInfo.getPid());
        if (adListener != null) {
            interstitialAD.setADListener(new GDTInterstitialADListenerIml(this, new AdListenerWrapper(this, adListener)));
        }
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        interstitialAD.loadAD();
    }

    @Override
    public void destroy() {
        if(interstitialAD!=null){
            interstitialAD.destroy();
        }
    }

    public void showAD() {
        interstitialAD.show();
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

    public Activity getActivity() {
        return activity;
    }
}
