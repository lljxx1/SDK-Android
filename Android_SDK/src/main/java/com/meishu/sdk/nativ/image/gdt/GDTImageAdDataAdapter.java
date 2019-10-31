package com.meishu.sdk.nativ.image.gdt;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.nativ.image.ImageAdInteractionListener;
import com.meishu.sdk.nativ.image.ImageAdData;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.ads.nativ.widget.NativeAdContainer;

import java.util.List;

public class GDTImageAdDataAdapter implements ImageAdData {

    private NativeUnifiedADData gdtNativeADData;
    private GDTNativeAdListenerImpl adListener;

    public GDTImageAdDataAdapter(@NonNull NativeUnifiedADData gdtNativeADData, GDTNativeAdListenerImpl adListener) {
        this.gdtNativeADData = gdtNativeADData;
        this.adListener = adListener;
    }

    @Override
    public String getTitle() {
        return gdtNativeADData.getTitle();
    }

    @Override
    public String getDesc() {
        return gdtNativeADData.getDesc();
    }

    @Override
    public String getIconUrl() {
        return gdtNativeADData.getIconUrl();
    }

    @Override
    public int getAdPatternType() {
        return gdtNativeADData.getAdPatternType();
    }

    @Override
    public String[] getImgList() {
        String[] imgs = null;
        if (gdtNativeADData.getImgList() != null) {
            imgs = gdtNativeADData.getImgList().toArray(new String[0]);
        }
        return imgs;
    }

    @Override
    public void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, ImageAdInteractionListener nativeAdEventListener) {
        NativeAdContainer gdtNativeAdContainer = new NativeAdContainer(activity);
        ViewGroup parent = (ViewGroup) (adContainer.getParent());
        parent.removeView(adContainer);
        gdtNativeAdContainer.addView(adContainer);
        parent.addView(gdtNativeAdContainer);
        gdtNativeADData.bindAdToView(activity, gdtNativeAdContainer, null, clickableViews);
        gdtNativeADData.setNativeAdEventListener(new GDTNativeAdEventListenerImpl(this, nativeAdEventListener));
    }

    @Override
    public void destroy() {
        if (gdtNativeADData != null) {
            gdtNativeADData.destroy();
        }
    }

    @Override
    public int getInteractionType() {
        return this.gdtNativeADData.isAppAd() ? 1 : 0;
    }

    public GDTNativeAdListenerImpl getAdListener() {
        return adListener;
    }
}
