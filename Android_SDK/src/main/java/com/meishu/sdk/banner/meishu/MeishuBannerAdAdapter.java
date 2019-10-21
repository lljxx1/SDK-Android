package com.meishu.sdk.banner.meishu;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.meishu_ad.banner.BannerAd;

public class MeishuBannerAdAdapter extends BaseAdData implements com.meishu.sdk.banner.BannerAd {
    private BannerAd nativeBannerAd;
    private BannerInteractionListener interactionListener;

    public MeishuBannerAdAdapter(@NonNull BannerAd nativeBannerAd) {
        this.nativeBannerAd = nativeBannerAd;
    }

    @Override
    public View getAdView() {
        View adView=this.nativeBannerAd.getAdView();
        ViewGroup parent = (ViewGroup) adView.getParent();
        if(parent!=null){
            parent.removeView(adView);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this.nativeBannerAd));
        touchContainer.addView(adView);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        return touchContainer;
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
        nativeBannerAd.setInteractionListener(new MeishuBannerInteractionListener(this.nativeBannerAd, listener));
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
