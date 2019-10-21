package com.meishu.sdk.banner.gdt;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;

public class GDTBannerAd extends BaseAdData implements BannerAd {

    private View bannerView;
    private BannerInteractionListener interactionListener;

    public GDTBannerAd(@NonNull View bannerView) {
        ViewGroup parent = (ViewGroup) bannerView.getParent();
        if(parent!=null){
            parent.removeView(bannerView);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(bannerView.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this));
        touchContainer.addView(bannerView);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        this.bannerView = touchContainer;
    }

    @Override
    public View getAdView() {
        return bannerView;
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }
}
