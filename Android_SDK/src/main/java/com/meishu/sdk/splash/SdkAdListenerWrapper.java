package com.meishu.sdk.splash;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class SdkAdListenerWrapper implements SplashAdListener {
    private DelegateChain delegateChain;
    private SplashAdListener apiAdListener;

    public SdkAdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable SplashAdListener apiAdListener) {
        this.delegateChain = delegateChain;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onLoaded(SplashAd ad) {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(), this.delegateChain.getSdkAdInfo().getRsp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onLoaded(ad);
        }
    }

    @Override
    public void onAdExposure() {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(), this.delegateChain.getSdkAdInfo().getImp(), new DefaultHttpGetWithNoHandlerCallback());
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
