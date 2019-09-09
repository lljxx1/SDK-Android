package com.meishu.sdk;

import android.app.Activity;

import com.meishu.sdk.domain.SdkAdInfo;

public interface DelegateChain {
    void setNext(DelegateChain next);

    DelegateChain getNext();

    SdkAdInfo getSdkAdInfo();

    Activity getActivity();

    void loadAd();

}
