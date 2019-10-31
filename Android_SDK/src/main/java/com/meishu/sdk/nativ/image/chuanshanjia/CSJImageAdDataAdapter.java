package com.meishu.sdk.nativ.image.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTImage;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.meishu.sdk.nativ.image.ImageAdInteractionListener;
import com.meishu.sdk.nativ.image.ImageAdData;

import java.util.ArrayList;
import java.util.List;

public class CSJImageAdDataAdapter implements ImageAdData {

    private TTNativeAd ttNativeAd;
    private CSJNativeAdListener adListener;

    public CSJImageAdDataAdapter(@NonNull TTNativeAd ttNativeAd, CSJNativeAdListener adListener) {
        this.ttNativeAd = ttNativeAd;
        this.adListener = adListener;
    }

    @Override
    public String getTitle() {
        return ttNativeAd.getTitle();
    }

    @Override
    public String getDesc() {
        return ttNativeAd.getDescription();
    }

    @Override
    public String getIconUrl() {
        return ttNativeAd.getIcon().getImageUrl();
    }

    @Override
    public int getAdPatternType() {
        return ttNativeAd.getInteractionType();
    }

    @Override
    public String[] getImgList() {
        List<TTImage> images = ttNativeAd.getImageList();
        if (images != null) {
            List<String> imageUrls = new ArrayList<>();
            for (TTImage image : images) {
                imageUrls.add(image.getImageUrl());
            }
            return imageUrls.toArray(new String[0]);
        } else {
            return null;
        }
    }


    @Override
    public void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, ImageAdInteractionListener imageAdInteractionListener) {
        ttNativeAd.registerViewForInteraction(adContainer, clickableViews, null, new CSJAdInteractionListener(this, imageAdInteractionListener));
    }

    @Override
    public void destroy() {
        //do nothing
    }

    @Override
    public int getInteractionType() {
        int interactionType = 0;
        switch (ttNativeAd.getInteractionType()){
            case TTAdConstant.INTERACTION_TYPE_BROWSER:
            case TTAdConstant.INTERACTION_TYPE_LANDING_PAGE:
                interactionType=0;
                break;
            case TTAdConstant.INTERACTION_TYPE_DOWNLOAD:
                interactionType=1;
                break;
            case TTAdConstant.INTERACTION_TYPE_DIAL:
                break;
        }
        return interactionType;
    }

    public CSJNativeAdListener getAdListener() {
        return adListener;
    }
}
