package com.meishu.sdk.banner;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class SdkAdListenerWrapper implements BannerAdListener {
    private DelegateChain delegateChain;
    private BannerAdListener apiAdListener;

    public SdkAdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable BannerAdListener apiAdListener) {
        this.delegateChain = delegateChain;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onLoaded(BannerAd bannerAd) {
        HttpUtil.asyncGetWithWebViewUA(bannerAd.getAdView().getContext(),this.delegateChain.getSdkAdInfo().getRsp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onLoaded(bannerAd);
        }
    }

    @Override
    public void onAdExposure() {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getAdLoader().getActivity(),this.delegateChain.getSdkAdInfo().getImp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdExposure();
        }
    }

    @Override
    public void onAdClosed() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdClosed();
        }
    }

    @Override
    public void onError() {
        if (delegateChain.getNext() != null) {
            AdLoader adLoader = delegateChain.getAdLoader();
            DelegateChain current = delegateChain.getNext();//当前节点改为下一个节点
            adLoader.setCurrent(current);//记录当前节点，用于destroy时回收当前广告的资源
            current.loadAd();
        } else if (this.apiAdListener != null) {
            this.apiAdListener.onError();
        } else {
        }
    }
}
