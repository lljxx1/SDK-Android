package com.meishu.sdk;

import android.app.Activity;

import com.meishu.sdk.domain.SdkAdInfo;

public interface AdDelegate {
    void loadAd();

    void destroy();

    AdLoader getAdLoader();

    SdkAdInfo getSdkAdInfo();

    Activity getActivity();
}
