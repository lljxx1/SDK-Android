package com.meishu.sdk.nativ.recycler.meishu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.nativ.recycler.NativeAdData;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.nativ.recycler.AdMediaListener;
import com.meishu.sdk.nativ.recycler.NativeAdUtils;

import java.util.List;

public class MeishuNativeAdDataAdapter implements NativeAdData {
    private com.meishu.sdk.meishu_ad.nativ.NativeAdData nativeAdData;
    private MeishuAdNativeWrapper adWrapper;

    public MeishuNativeAdDataAdapter(@NonNull MeishuAdNativeWrapper adWrapper, @NonNull com.meishu.sdk.meishu_ad.nativ.NativeAdData nativeAdData) {
        this.adWrapper = adWrapper;
        this.nativeAdData = nativeAdData;
    }

    @Override
    public int getAdPatternType() {
        return this.nativeAdData.getAdPatternType();
    }

    @Override
    public int getInteractionType() {
        return this.nativeAdData.getInteractionType();
    }

    @Override
    public void bindAdToView(Activity activity,@NonNull ViewGroup container, List<View> clickableViews, AdInteractionListener adInteractionListener) {

        ViewGroup parent = (ViewGroup) container.getParent();
        if(parent!=null){
            parent.removeView(container);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(container.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this.nativeAdData));
        touchContainer.addView(container);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        container=touchContainer;

        NativeAdUtils.removeGdtNativeAdContainer(container);//广点通接口可能会给container添加parent，故在此清理
        this.nativeAdData.bindAdToView(activity, container, clickableViews, new MeishuInteractionListenerAdapter(this.nativeAdData, adInteractionListener));
    }

    @Override
    public void bindMediaView(ViewGroup mediaView, AdMediaListener adMediaListener) {
//        NativeAdUtils.removeGdtMediaView(mediaView);
        mediaView.removeAllViews();
        this.nativeAdData.bindMediaView(mediaView, new MeishuAdMediaListenerAdapter(this.adWrapper, adMediaListener));
    }

    @Override
    public String getIconUrl() {
        return this.nativeAdData.getIconUrl();
    }

    @Override
    public String[] getImgUrls() {
        return this.nativeAdData.getImgUrls();
    }

    @Override
    public String getTitle() {
        return this.nativeAdData.getTitle();
    }

    @Override
    public String getDesc() {
        return this.nativeAdData.getDesc();
    }

    @Override
    public void destroy() {
        //do nothing
    }
}
