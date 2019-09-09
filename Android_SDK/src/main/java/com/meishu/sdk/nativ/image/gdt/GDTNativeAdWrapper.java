package com.meishu.sdk.nativ.image.gdt;

import android.content.Context;

import com.meishu.sdk.nativ.image.NativeAdDelegate;
import com.meishu.sdk.nativ.image.NativeAdListener;
import com.qq.e.ads.nativ.NativeUnifiedAD;

public class GDTNativeAdWrapper implements NativeAdDelegate {
    private NativeUnifiedAD nativeUnifiedAD;

    public GDTNativeAdWrapper(Context context, String appId, String posId, NativeAdListener listener) {
        this.nativeUnifiedAD = new NativeUnifiedAD(context, appId, posId, new GDTNativeAdListenerImpl(listener));
    }

    @Override
    public void loadData() {
        nativeUnifiedAD.loadData(1);
    }
}
