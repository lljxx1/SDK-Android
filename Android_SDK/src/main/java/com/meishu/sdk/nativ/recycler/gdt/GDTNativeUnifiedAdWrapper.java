package com.meishu.sdk.nativ.recycler.gdt;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.nativ.recycler.AdListenerWrapper;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;
import com.meishu.sdk.nativ.recycler.RecyclerAdLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.nativ.NativeUnifiedAD;

public class GDTNativeUnifiedAdWrapper extends BaseSdkAdWrapper {

    private NativeUnifiedAD nativeUnifiedAD;
    private RecyclerAdLoader adLoader;

    public GDTNativeUnifiedAdWrapper(@NonNull RecyclerAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        nativeUnifiedAD = new NativeUnifiedAD(getActivity(), sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new GDTNativeAdListenerAdapter(this, new AdListenerWrapper(this, adLoader.getApiAdListener())));
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        nativeUnifiedAD.loadData(1);
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public RecyclerAdListener getAdListener() {
        return this.adLoader.getApiAdListener();
    }

}
