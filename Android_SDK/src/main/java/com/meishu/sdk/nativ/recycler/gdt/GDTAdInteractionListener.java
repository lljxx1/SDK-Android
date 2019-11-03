package com.meishu.sdk.nativ.recycler.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.recycler.RecylcerAdInteractionListener;
import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.nativ.NativeADEventListener;
import com.qq.e.comm.util.AdError;

public class GDTAdInteractionListener implements NativeADEventListener {
    private static final String TAG = "GDTAdInteractionListene";
    private RecylcerAdInteractionListener meishuRecylcerAdInteractionListener;
    private GDTNativeRecyclerAdDataAdapter adData;

    public GDTAdInteractionListener(@NonNull GDTNativeRecyclerAdDataAdapter adData, RecylcerAdInteractionListener meishuRecylcerAdInteractionListener) {
        this.adData = adData;
        this.meishuRecylcerAdInteractionListener = meishuRecylcerAdInteractionListener;
    }

    @Override
    public void onADClicked() {
        if (this.adData.getAdWrapper() != null && this.adData.getAdWrapper().getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    adData.getAdWrapper().getActivity(),
                    ClickHandler.replaceOtherMacros(this.adData.getAdWrapper().getSdkAdInfo().getClk(), this.adData),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (this.meishuRecylcerAdInteractionListener != null) {
            this.meishuRecylcerAdInteractionListener.onAdClicked();
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
