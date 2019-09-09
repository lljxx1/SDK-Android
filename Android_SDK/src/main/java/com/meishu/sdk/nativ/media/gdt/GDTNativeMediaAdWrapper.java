package com.meishu.sdk.nativ.media.gdt;

import android.content.Context;

import com.meishu.sdk.nativ.media.NativeMediaAdDelegate;
import com.meishu.sdk.nativ.media.NativeMediaAdListener;
import com.qq.e.ads.nativ.NativeMediaAD;

public class GDTNativeMediaAdWrapper implements NativeMediaAdDelegate {
    private NativeMediaAD nativeMediaAd;

    public GDTNativeMediaAdWrapper(Context context, String appId, String posId, NativeMediaAdListener listener) {
        this.nativeMediaAd = new NativeMediaAD(context, appId, posId, new GDTNativeAdListenerImpl(listener));
    }

    @Override
    public void loadAD() {
        nativeMediaAd.loadAD(1);
    }
}
