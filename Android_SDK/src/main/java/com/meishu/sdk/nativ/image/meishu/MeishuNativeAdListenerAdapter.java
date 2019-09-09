package com.meishu.sdk.nativ.image.meishu;

import com.meishu.sdk.nativ.image.NativeAdData;
import com.meishu.sdk.nativ.recycler.AdData;
import com.meishu.sdk.nativ.recycler.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

public class MeishuNativeAdListenerAdapter implements NativeAdListener {
    private com.meishu.sdk.nativ.image.NativeAdListener internalAdListener;

    public MeishuNativeAdListenerAdapter(com.meishu.sdk.nativ.image.NativeAdListener internalAdListener) {
        this.internalAdListener = internalAdListener;
    }

    @Override
    public void onAdLoaded(List<AdData> adDatas) {
        if (adDatas != null) {
            List<NativeAdData> nativeAdDatas = new ArrayList<>();
            for (AdData adData : adDatas) {
                nativeAdDatas.add(new MeishuNativeAdDataAdapter(adData));
            }
            this.internalAdListener.onAdLoaded(nativeAdDatas);
        }
    }

    @Override
    public void onAdExposure() {
        if (this.internalAdListener != null) {
            this.internalAdListener.onAdExposure();
        }
    }

    @Override
    public void onAdClosed() {
    }

    @Override
    public void onAdError() {
        if (this.internalAdListener != null) {
            this.internalAdListener.onAdError();
        }
    }
}
