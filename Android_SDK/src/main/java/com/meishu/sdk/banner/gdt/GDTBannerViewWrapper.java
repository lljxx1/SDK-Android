package com.meishu.sdk.banner.gdt;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.meishu.sdk.banner.AdListenerWrapper;
import com.meishu.sdk.banner.BannerAdListener;
import com.meishu.sdk.banner.BannerAdDelegate;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.BannerView;

public class GDTBannerViewWrapper implements DelegateChain, BannerAdDelegate {

    private BannerView gdtBannerView;
    private DelegateChain next;
    private SdkAdInfo sdkAdInfo;
    private Activity activity;

    public GDTBannerViewWrapper(@NonNull Activity context, @NonNull SdkAdInfo sdkAdInfo, @Nullable BannerAdListener bannerADListener) {
        this.activity = context;
        this.sdkAdInfo = sdkAdInfo;
        gdtBannerView = new BannerView(context, ADSize.BANNER, sdkAdInfo.getApp_id(), sdkAdInfo.getPid());
        if (bannerADListener != null) {
            gdtBannerView.setADListener(new GDTBannerAdListenerImpl(this, new AdListenerWrapper(this, bannerADListener)));
        }
    }

    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.activity, this.sdkAdInfo.getReq(), new DefaultHttpGetWithNoHandlerCallback());
        gdtBannerView.loadAD();
    }

    public View getAdView() {
        return gdtBannerView;
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

    @Override
    public void destroy(){
        gdtBannerView.destroy();
    }
}
