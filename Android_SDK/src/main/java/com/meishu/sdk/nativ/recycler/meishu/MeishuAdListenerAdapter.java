package com.meishu.sdk.nativ.recycler.meishu;

import com.meishu.sdk.meishu_ad.nativ.AdListener;
import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.nativ.recycler.AdData;
import com.meishu.sdk.nativ.recycler.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

public class MeishuAdListenerAdapter implements AdListener {
    private NativeAdListener apiAdListener;

    public MeishuAdListenerAdapter(NativeAdListener apiAdListener) {
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(List<NativeAdData> adDatas) {
        if (this.apiAdListener != null && adDatas != null) {
            List<AdData> apiAdDatas = new ArrayList<>();
            for (NativeAdData adData : adDatas) {
                apiAdDatas.add(new MeishuAdDataAdapter(adData));
            }
            this.apiAdListener.onAdLoaded(apiAdDatas);
        }
    }

    @Override
    public void onADExposure() {
        if(this.apiAdListener!=null){
            this.apiAdListener.onAdExposure();
        }
    }

    @Override
    public void onAdError() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
