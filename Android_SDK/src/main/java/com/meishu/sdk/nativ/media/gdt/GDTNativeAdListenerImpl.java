package com.meishu.sdk.nativ.media.gdt;

import android.util.Log;

import com.meishu.sdk.nativ.media.NativeMediaAdData;
import com.meishu.sdk.nativ.media.NativeMediaAdListener;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;

public class GDTNativeAdListenerImpl implements NativeMediaAD.NativeMediaADListener {
    private static final String TAG = "GDTNativeAdListenerImpl";
    private NativeMediaAdListener nativeMediaAdListener;

    public GDTNativeAdListenerImpl(NativeMediaAdListener nativeMediaAdListener) {
        this.nativeMediaAdListener = nativeMediaAdListener;
    }

    @Override
    public void onADLoaded(List<NativeMediaADData> list) {
        if (this.nativeMediaAdListener != null && list != null && list.size() > 0) {
            List<NativeMediaAdData> meishuMediaAdDatas = new ArrayList<>();
            for (NativeMediaADData gdtMediaAdData : list) {
                meishuMediaAdDatas.add(new GDTNativeMediaAdDataAdapter(gdtMediaAdData));
            }
            this.nativeMediaAdListener.onAdLoaded(meishuMediaAdDatas);
        }
    }

    @Override
    public void onADStatusChanged(NativeMediaADData nativeMediaADData) {
        Log.d(TAG, "onADStatusChanged: ");
    }

    @Override
    public void onADError(NativeMediaADData nativeMediaADData, AdError adError) {
        Log.d(TAG, "onADError: ");
    }

    @Override
    public void onADVideoLoaded(NativeMediaADData nativeMediaADData) {
        if (this.nativeMediaAdListener != null) {
            this.nativeMediaAdListener.onVideoLoaded(new GDTNativeMediaAdDataAdapter(nativeMediaADData));
        }
    }

    @Override
    public void onADExposure(NativeMediaADData nativeMediaADData) {
        if (this.nativeMediaAdListener != null) {
            this.nativeMediaAdListener.onADExposure(new GDTNativeMediaAdDataAdapter(nativeMediaADData));
        }
    }

    @Override
    public void onADClicked(NativeMediaADData nativeMediaADData) {
        if (this.nativeMediaAdListener != null) {
            this.nativeMediaAdListener.onAdClicked(new GDTNativeMediaAdDataAdapter(nativeMediaADData));
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        Log.d(TAG, "onNoAD: ");
    }
}
