package com.meishu.sdk;

import android.app.Activity;

import com.meishu.sdk.domain.SdkAdInfo;

public abstract class BaseMeishuWrapper implements DelegateChain {

    private Activity activity;

    public BaseMeishuWrapper(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setNext(DelegateChain next) {
        //do nothing
    }

    @Override
    public DelegateChain getNext() {
        return null;//业务决定，美数广告是尾结点
    }

    @Override
    public SdkAdInfo getSdkAdInfo() {
        return new SdkAdInfo();//美数广告不用设置sdk相关信息，这些信息用于其他sdk
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }

    @Override
    public void destroy() {
        //do nothing
    }
}
