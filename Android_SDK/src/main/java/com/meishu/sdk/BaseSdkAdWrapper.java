package com.meishu.sdk;

import android.app.Activity;

import com.meishu.sdk.domain.SdkAdInfo;

public abstract class BaseSdkAdWrapper implements DelegateChain {
    private Activity activity;
    private DelegateChain next;
    private SdkAdInfo sdkAdInfo;

    public BaseSdkAdWrapper(Activity activity, SdkAdInfo sdkAdInfo) {
        this.activity = activity;
        this.next = next;
        this.sdkAdInfo = sdkAdInfo;
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

    @Override
    public void destroy() {
//do nothing
    }

}
