package com.meishu.sdk.nativ.recycler.meishu;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseMeishuWrapper;
import com.meishu.sdk.meishu_ad.AdNative;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.meishu_ad.nativ.NativeAdWrapper;
import com.meishu.sdk.nativ.recycler.RecyclerAdLoader;

public class MeishuAdNativeWrapper extends BaseMeishuWrapper implements NativeAdWrapper {
    private AdNative adNative;
    private NativeAdSlot adSlot;
    private RecyclerAdLoader adLoader;
    private MeishuAdListenerAdapter meishuAdListenerAdapter;

    public MeishuAdNativeWrapper(@NonNull RecyclerAdLoader adLoader, NativeAdSlot adSlot) {
        super(adLoader.getActivity());
        this.adLoader = adLoader;
        this.adNative = new AdNative(adLoader.getActivity());
        this.adSlot = adSlot;
    }

    @Override
    public void loadAd() {
        this.meishuAdListenerAdapter=new MeishuAdListenerAdapter(this, this.adLoader.getApiAdListener());
        adNative.loadNativeAd(this.adSlot, this.meishuAdListenerAdapter);
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public NativeAdSlot getAdSlot() {
        return adSlot;
    }

    public MeishuAdListenerAdapter getMeishuAdListenerAdapter() {
        return meishuAdListenerAdapter;
    }
}
