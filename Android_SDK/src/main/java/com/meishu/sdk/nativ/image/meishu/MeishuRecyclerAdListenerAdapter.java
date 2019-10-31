package com.meishu.sdk.nativ.image.meishu;

import com.meishu.sdk.nativ.image.ImageAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;

import java.util.ArrayList;
import java.util.List;

public class MeishuRecyclerAdListenerAdapter implements RecyclerAdListener {
    private com.meishu.sdk.nativ.image.NativeAdListener internalAdListener;

    public MeishuRecyclerAdListenerAdapter(com.meishu.sdk.nativ.image.NativeAdListener internalAdListener) {
        this.internalAdListener = internalAdListener;
    }

    @Override
    public void onAdLoaded(List<RecyclerAdData> adDatas) {
        if (adDatas != null) {
            List<ImageAdData> imageAdData = new ArrayList<>();
            for (RecyclerAdData adData : adDatas) {
                imageAdData.add(new MeishuImageAdDataAdapter(adData));
            }
            this.internalAdListener.onAdLoaded(imageAdData);
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
