package com.meishu.sdk;

import android.app.Activity;

import com.meishu.sdk.domain.SdkAdInfo;

public interface DelegateChain extends AdDelegate{
    void setNext(DelegateChain next);

    DelegateChain getNext();

}
