package com.meishu.sdk.nativ.media;

import android.content.Context;

import com.meishu.sdk.nativ.media.gdt.GDTNativeMediaAdWrapper;

public class NativeMediaAd implements NativeMediaAdDelegate {
    private NativeMediaAdDelegate nativeMediaAdDelegate;

    public NativeMediaAd(Context context, String appId, String posId, NativeMediaAdListener listener) {
        this.nativeMediaAdDelegate = new GDTNativeMediaAdWrapper(context, appId, posId, listener);
    }

    @Override
    public void loadAD() {
        nativeMediaAdDelegate.loadAD();
    }
}
