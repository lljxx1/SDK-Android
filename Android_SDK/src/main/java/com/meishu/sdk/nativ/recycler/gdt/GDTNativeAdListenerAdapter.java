package com.meishu.sdk.nativ.recycler.gdt;

import com.meishu.sdk.nativ.recycler.RecyclerAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;
import com.qq.e.ads.nativ.NativeADUnifiedListener;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;

public class GDTNativeAdListenerAdapter implements NativeADUnifiedListener {
    private static final String TAG = "GDTNativeAdListenerAdap";
    private RecyclerAdListener meishuAdListener;
    private GDTNativeUnifiedAdWrapper adWrapper;

    public GDTNativeAdListenerAdapter(GDTNativeUnifiedAdWrapper adWrapper, RecyclerAdListener meishuAdListener) {
        this.adWrapper = adWrapper;
        this.meishuAdListener = meishuAdListener;
    }

    @Override
    public void onADLoaded(List<NativeUnifiedADData> list) {
        if (list != null && this.meishuAdListener != null) {
            List<RecyclerAdData> meishuAdDatas = new ArrayList<>();
            for (NativeUnifiedADData nativeUnifiedADData : list) {
                meishuAdDatas.add(new GDTNativeRecyclerAdDataAdapter(adWrapper,nativeUnifiedADData, this.meishuAdListener));
            }
            this.meishuAdListener.onAdLoaded(meishuAdDatas);
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        if (this.meishuAdListener != null) {
            this.meishuAdListener.onAdError();
        }
    }
}
