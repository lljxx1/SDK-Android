package com.meishu.sdk.nativ.recycler.gdt;

import android.app.Activity;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.nativ.recycler.AdListenerWrapper;
import com.meishu.sdk.nativ.recycler.NativeAdDelegate;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.nativ.NativeUnifiedAD;

public class GDTNativeUnifiedAdWrapper implements NativeAdDelegate, DelegateChain {

    private NativeUnifiedAD nativeUnifiedAD;
    private NativeAdListener adListener;
    private SdkAdInfo sdkAdInfo;
    private DelegateChain next;
    private Activity activity;

    public GDTNativeUnifiedAdWrapper(Activity activity, SdkAdInfo sdkAdInfo, NativeAdListener adListener) {
        this.activity = activity;
        this.adListener = adListener;
        this.sdkAdInfo = sdkAdInfo;
        nativeUnifiedAD = new NativeUnifiedAD(activity, sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new GDTNativeAdListenerAdapter(this, new AdListenerWrapper(this, adListener)));
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        nativeUnifiedAD.loadData(1);
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
