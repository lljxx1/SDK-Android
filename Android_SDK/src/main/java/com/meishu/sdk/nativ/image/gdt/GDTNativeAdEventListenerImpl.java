package com.meishu.sdk.nativ.image.gdt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.nativ.image.AdInteractionListener;
import com.qq.e.ads.nativ.NativeADEventListener;
import com.qq.e.comm.util.AdError;

public class GDTNativeAdEventListenerImpl implements NativeADEventListener {
    private static final String TAG = "GDTNativeAdEventListene";
    private GDTNativeAdDataAdapter adDataAdapter;
    private AdInteractionListener nativeADEventListener;

    public GDTNativeAdEventListenerImpl(@NonNull GDTNativeAdDataAdapter adDataAdapter, AdInteractionListener nativeADEventListener) {
        this.adDataAdapter = adDataAdapter;
        this.nativeADEventListener = nativeADEventListener;
    }

    @Override
    public void onADExposed() {
        if (this.adDataAdapter.getAdListener() != null) {
            this.adDataAdapter.getAdListener().onAdExposure();
        }
    }

    @Override
    public void onADClicked() {
        if (nativeADEventListener != null) {
            nativeADEventListener.onAdClicked();
        }
    }

    @Override
    public void onADError(AdError adError) {
        if (this.adDataAdapter.getAdListener() != null) {
            this.adDataAdapter.getAdListener().onNoAD(adError);
        }
    }

    @Override
    public void onADStatusChanged() {
        Log.d(TAG, "onADStatusChanged: ");
    }
}
