package com.meishu.sdk.nativ.recycler.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.nativ.NativeADEventListener;
import com.qq.e.comm.util.AdError;

public class GDTAdInteractionListener implements NativeADEventListener {
    private static final String TAG = "GDTAdInteractionListene";
    private AdInteractionListener meishuAdInteractionListener;
    private GDTNativeNativeAdDataAdapter adData;

    public GDTAdInteractionListener(@NonNull GDTNativeNativeAdDataAdapter adData, AdInteractionListener meishuAdInteractionListener) {
        this.adData = adData;
        this.meishuAdInteractionListener = meishuAdInteractionListener;
    }

    @Override
    public void onADClicked() {
        if (this.adData.getAdWrapper() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    adData.getAdWrapper().getActivity(),
                    ClickHandler.replaceMacros(this.adData.getAdWrapper().getSdkAdInfo().getClk(),this.adData),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (this.meishuAdInteractionListener != null) {
            this.meishuAdInteractionListener.onAdClicked();
        }
    }

    @Override
    public void onADExposed() {
        if (this.adData.getApiAdListener() != null) {
            this.adData.getApiAdListener().onAdExposure();
        }
    }

    @Override
    public void onADError(AdError adError) {
        Log.d(TAG, "onADError: ");
    }

    @Override
    public void onADStatusChanged() {
        Log.d(TAG, "onADStatusChanged: ");
    }
}
