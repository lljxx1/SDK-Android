package com.meishu.sdk.banner;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class AdListenerWrapper implements BannerAdListener {
    private DelegateChain delegateChain;
    private BannerAdListener apiAdListener;

    public AdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable BannerAdListener apiAdListener) {
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
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(),this.delegateChain.getSdkAdInfo().getImp(), new DefaultHttpGetWithNoHandlerCallback());
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
            delegateChain.getNext().loadAd();
        } else if (this.apiAdListener != null) {
            this.apiAdListener.onError();
        } else {
        }
    }
}
