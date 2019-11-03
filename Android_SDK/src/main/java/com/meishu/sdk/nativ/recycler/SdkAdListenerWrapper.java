package com.meishu.sdk.nativ.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

import java.util.List;

public class SdkAdListenerWrapper implements RecyclerAdListener {
    private DelegateChain delegateChain;
    private RecyclerAdListener apiAdListener;

    public SdkAdListenerWrapper(@NonNull DelegateChain delegateChain, @Nullable RecyclerAdListener apiAdListener) {
        this.delegateChain = delegateChain;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(List<RecyclerAdData> ads) {
        HttpUtil.asyncGetWithWebViewUA(this.delegateChain.getActivity(), this.delegateChain.getSdkAdInfo().getRsp(), new DefaultHttpGetWithNoHandlerCallback());
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdLoaded(ads);
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
    public void onAdError() {
        if (delegateChain.getNext() != null) {
            delegateChain.getNext().loadAd();
        } else if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        } else {
        }
    }
}
