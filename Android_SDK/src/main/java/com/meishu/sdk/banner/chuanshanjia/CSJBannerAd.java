package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.banner.BannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.domain.SdkAdInfo;

public class CSJBannerAd extends BaseAdData implements BannerAd {
    private TTBannerAd ttBannerAd;
    private BannerInteractionListener interactionListener;
    private SdkAdInfo sdkAdInfo;

    public CSJBannerAd(@NonNull SdkAdInfo sdkAdInfo, TTBannerAd ttBannerAd) {
        this.sdkAdInfo=sdkAdInfo;
        this.ttBannerAd = ttBannerAd;
    }

    @Override
    public View getAdView() {
        if (this.ttBannerAd != null) {
            View adView =this.ttBannerAd.getBannerView();
            ViewGroup parent = (ViewGroup) adView.getParent();
            if(parent!=null){
                parent.removeView(adView);
            }
            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
            touchContainer.setTouchPositionListener(new TouchPositionListener(this));
            touchContainer.addView(adView);
            if(parent!=null){
                parent.addView(touchContainer);
            }
            return touchContainer;
        } else {
            return null;
        }
    }

    @Override
    public void setInteractionListener(BannerInteractionListener listener) {
        this.interactionListener = listener;
        if (this.ttBannerAd != null) {
            this.ttBannerAd.setBannerInteractionListener(new CSJBannerInteractionListenerImpl(this,listener));
        }
    }

    @Override
    public BannerInteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    public SdkAdInfo getSdkAdInfo() {
        return sdkAdInfo;
    }
}
