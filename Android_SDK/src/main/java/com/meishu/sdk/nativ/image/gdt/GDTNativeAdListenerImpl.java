package com.meishu.sdk.nativ.image.gdt;

import com.meishu.sdk.nativ.image.NativeAdData;
import com.meishu.sdk.nativ.image.NativeAdListener;
import com.qq.e.ads.nativ.NativeADUnifiedListener;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;

public class GDTNativeAdListenerImpl implements NativeADUnifiedListener {
    private static final String TAG = "GDTNativeADListenerImpl";
    private NativeAdListener nativeADListener;

    public GDTNativeAdListenerImpl(NativeAdListener nativeADListener) {
        this.nativeADListener = nativeADListener;
    }

    @Override
    public void onADLoaded(List<NativeUnifiedADData> list) {
        if (list != null) {
            List<NativeAdData> meishuADDataList = new ArrayList<>();
            for (NativeUnifiedADData nativeUnifiedADData : list) {
                meishuADDataList.add(new GDTNativeAdDataAdapter(nativeUnifiedADData, this));
            }
            nativeADListener.onAdLoaded(meishuADDataList);
        }
    }

    public void onAdExposure() {
        if (this.nativeADListener != null) {
            this.nativeADListener.onAdExposure();
        }
    }

    /*public void onAdClosed() {
        if (this.nativeADListener != null) {
            this.nativeADListener.onAdClosed();
        }
    }*/

    @Override
    public void onNoAD(AdError adError) {
        if (this.nativeADListener != null) {
            this.nativeADListener.onAdError();
        }
    }
}
