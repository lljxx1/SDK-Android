package com.meishu.sdk.interstitial;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class AdListenerWrapper implements InterstitialAdListener {
    private DelegateChain delegateChain;
    private InterstitialAdListener apiAdListener;

    public AdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable InterstitialAdListener apiAdListener) {
        this.delegateChain = delegateChain;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(InterstitialAd ad) {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(),this.delegateChain.getSdkAdInfo().getRsp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdLoaded(ad);
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
    public void onAdError() {
        if (delegateChain.getNext() != null) {
            delegateChain.getNext().loadAd();
        } else if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        } else {
        }
    }
}
