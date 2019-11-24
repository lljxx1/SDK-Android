package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;
import android.view.View;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.splash.SplashInteractionListener;

public class MeishuSplashAdAdapter extends BaseAdData implements com.meishu.sdk.splash.SplashAd {
    private NativeSplashAd nativeAd;
    private SplashInteractionListener interactionListener;
    private View adView;

    public MeishuSplashAdAdapter(@NonNull NativeSplashAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    @Override
    public View getAdView() {
        return this.adView;
    }

    public void setAdView(View adView) {
        this.adView = adView;
    }

    @Override
    public void setInteractionListener(SplashInteractionListener listener) {
        this.interactionListener = listener;
        nativeAd.setInteractionListener(new MeishuSplashInteractionListener(this, listener));
    }

    @Override
    public SplashInteractionListener getInteractionListener() {
        return this.interactionListener;
    }


    public int getInteractionType() {
        return this.nativeAd.getInteractionType();
    }

    public NativeSplashAd getNativeAd() {
        return nativeAd;
    }
}
