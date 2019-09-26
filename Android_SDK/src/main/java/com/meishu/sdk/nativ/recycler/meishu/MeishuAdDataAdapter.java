package com.meishu.sdk.nativ.recycler.meishu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.nativ.recycler.AdData;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.nativ.recycler.AdMediaListener;
import com.meishu.sdk.nativ.recycler.NativeAdUtils;

import java.util.List;

public class MeishuAdDataAdapter implements AdData {
    private NativeAdData nativeAdData;
    private MeishuAdNativeWrapper adWrapper;

    public MeishuAdDataAdapter(@NonNull MeishuAdNativeWrapper adWrapper, @NonNull NativeAdData nativeAdData) {
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
    public void bindAdToView(Activity activity, ViewGroup container, List<View> clickableViews, AdInteractionListener adInteractionListener) {
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
