package com.meishu.sdk.reward;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class SdkAdListenerWrapper implements RewardVideoAdListener {
    private DelegateChain delegateChain;
    private RewardVideoAdListener apiAdListener;

    public SdkAdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable RewardVideoAdListener apiAdListener) {
        this.delegateChain = delegateChain;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(RewardVideoAd ad) {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(),this.delegateChain.getSdkAdInfo().getRsp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdLoaded(ad);
        }
    }

    @Override
    public void onVideoCached() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onVideoCached();
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
    public void onReward() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onReward();
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
