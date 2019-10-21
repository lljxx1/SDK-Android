package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.bytedance.sdk.openadsdk.TTImage;
import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.MeishuAdPatternType;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.nativ.recycler.NativeAdData;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.nativ.recycler.AdMediaListener;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.nativ.recycler.NativeAdUtils;

import java.util.ArrayList;
import java.util.List;

public class CSJNativeAdDataAdapter extends BaseAdData implements NativeAdData {
    private static final String TAG = "CSJAdDataAdapter";
    private TTFeedAd ttFeedAd;

    private NativeAdListener adListener;
    private CSJTTAdNativeWrapper adNativeWrapper;

    public CSJNativeAdDataAdapter(@NonNull CSJTTAdNativeWrapper adNativeWrapper, @NonNull TTFeedAd ttFeedAd) {
        this.adNativeWrapper = adNativeWrapper;
        this.adListener = adNativeWrapper.getAdListener();
        this.ttFeedAd = ttFeedAd;
    }

    @Override
    public int getAdPatternType() {
        int imageMode = ttFeedAd.getImageMode();
        int adPatternType = -9999;
        switch (imageMode) {
            case TTAdConstant.IMAGE_MODE_VIDEO:
                adPatternType = MeishuAdPatternType.VIDEO;
                break;
            case TTAdConstant.IMAGE_MODE_LARGE_IMG:
            case TTAdConstant.IMAGE_MODE_SMALL_IMG:
            case TTAdConstant.IMAGE_MODE_GROUP_IMG:
                adPatternType = MeishuAdPatternType.IMAGE;
                break;
        }
        return adPatternType;
    }

    @Override
    public int getInteractionType() {
        int interactionType = 0;
        switch (ttFeedAd.getInteractionType()){
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

    @Override
    public void bindAdToView(Activity activity,@NonNull ViewGroup container, List<View> creativeClickableViews, AdInteractionListener adInteractionListener) {
        ViewGroup parent = (ViewGroup) container.getParent();
        if(parent!=null){
            parent.removeView(container);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(container.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this));
        touchContainer.addView(container);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        container=touchContainer;

        NativeAdUtils.removeGdtNativeAdContainer(container);//广点通接口可能会给container添加parent，故在此清理
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(container.getRootView());

        //创意点击views是指，点击对应的views会执行广告的目的，比如下载app、跳转目标网页等
        //clickableViews，在视频类广告中，点击clickableViews会跳转到视频页面
        //viewGroup参数必须为container的根view，否则穿山甲无法加载广告
        ttFeedAd.registerViewForInteraction((ViewGroup) container.getRootView(), clickableViews, creativeClickableViews, new CSJAdInteractionListenerAdapter(this, adInteractionListener));
    }

    @Override
    public void bindMediaView(ViewGroup mediaView, AdMediaListener nativeAdMediaListener) {
//        NativeAdUtils.removeGdtMediaView(mediaView);
        ttFeedAd.setVideoAdListener(new CSJVideoAdListenerImpl(nativeAdMediaListener));
        mediaView.removeAllViews();
        mediaView.addView(ttFeedAd.getAdView());
    }

    @Override
    public String getIconUrl() {
        return ttFeedAd.getIcon().getImageUrl();
    }

    @Override
    public String[] getImgUrls() {
        //穿山甲逻辑：大图、一图、三图，图片都在ImageList中
        String imgUrls[] = null;
        List<TTImage> images = ttFeedAd.getImageList();
        if (images != null) {
            imgUrls = new String[images.size()];
            int i = 0;
            for (TTImage image : images) {
                imgUrls[i++] = image.getImageUrl();
            }
        }
        return imgUrls;
    }

    @Override
    public String getTitle() {
        return ttFeedAd.getTitle();
    }

    @Override
    public String getDesc() {
        return ttFeedAd.getDescription();
    }

    @Override
    public void destroy() {
        //do nothing
    }

    public NativeAdListener getAdListener() {
        return this.adListener;
    }

    public CSJTTAdNativeWrapper getAdWrapper() {
        return adNativeWrapper;
    }
}
