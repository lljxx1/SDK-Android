package com.meishu.sdk.nativ.recycler.gdt;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.BaseAdData;
import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.nativ.recycler.RecyclerAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdMediaListener;
import com.meishu.sdk.nativ.recycler.RecyclerAdUtils;
import com.meishu.sdk.nativ.recycler.RecylcerAdInteractionListener;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.ads.nativ.widget.NativeAdContainer;
import com.qq.e.comm.constants.AdPatternType;

import java.util.List;

public class GDTNativeRecyclerAdDataAdapter extends BaseAdData implements RecyclerAdData {
    private static final String TAG = "GDTNativeAdDataAdapter";
    private NativeUnifiedADData nativeUnifiedADData;
    private GDTNativeUnifiedAdWrapper adWrapper;
    private volatile boolean hasExposed;

    public GDTNativeRecyclerAdDataAdapter(@NonNull GDTNativeUnifiedAdWrapper adWrapper, @NonNull NativeUnifiedADData nativeUnifiedADData) {
        this.adWrapper = adWrapper;
        this.nativeUnifiedADData = nativeUnifiedADData;
    }

    @Override
    public int getAdPatternType() {
        return this.nativeUnifiedADData.getAdPatternType();
    }

    @Override
    public int getInteractionType() {
        return this.nativeUnifiedADData.isAppAd() ? 1 : 0;
    }

    @Override
    public void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, RecylcerAdInteractionListener meishuRecylcerAdInteractionListener) {
        RecyclerAdUtils.removeGdtNativeAdContainer(adContainer);//广点通接口可能会给container添加parent，故在此清理
        RecyclerAdUtils.removeTouchAdContainer(adContainer);//所有接口都会给container添加TouchAdContainer，故在此清理

        ViewGroup parent = (ViewGroup) adContainer.getParent();
        if(parent!=null){
            parent.removeView(adContainer);
        }
        TouchAdContainer touchContainer = new TouchAdContainer(adContainer.getContext());
        touchContainer.setTouchPositionListener(new TouchPositionListener(this));
        touchContainer.addView(adContainer);
        if(parent!=null){
            parent.addView(touchContainer);
        }
        adContainer=touchContainer;

        NativeAdContainer gdtNativeAdContainer = new NativeAdContainer(activity);//NativeAdContainer必须为adContainer父容器，否则点击无效
        if (parent != null) {
            parent.removeView(adContainer);
            gdtNativeAdContainer.addView(adContainer);
            parent.addView(gdtNativeAdContainer);
        } else {
            gdtNativeAdContainer.addView(adContainer);
        }
        nativeUnifiedADData.bindAdToView(activity, gdtNativeAdContainer, null, clickableViews);
        nativeUnifiedADData.setNativeAdEventListener(new GDTAdInteractionListener(this, meishuRecylcerAdInteractionListener));
    }

    @Override
    public void bindMediaView(ViewGroup mediaView, RecyclerAdMediaListener nativeRecyclerAdMediaListener) {
//        mediaView.setVisibility(View.INVISIBLE);//必须设置mediaview不可见，否则会遮挡实际视频画面

        mediaView.removeAllViews();
        MediaView gdtMediaView = new MediaView(mediaView.getContext());
        mediaView.addView(gdtMediaView);

//        NativeAdUtils.removeGdtMediaView(mediaView);
//
//        MediaView gdtMediaView = new MediaView(mediaView.getContext());
//        ViewGroup parent = (ViewGroup) mediaView.getParent();
//        if (parent != null) {
//            parent.removeView(mediaView);
//            gdtMediaView.addView(mediaView);
//            parent.addView(gdtMediaView);
//        } else {
//            gdtMediaView.addView(mediaView);
//        }

        VideoOption.Builder builder = new VideoOption.Builder();

        builder.setAutoPlayPolicy(VideoOption.AutoPlayPolicy.WIFI);
        builder.setAutoPlayMuted(false);
        builder.setNeedCoverImage(true);
        builder.setNeedProgressBar(true);
        builder.setEnableDetailPage(false);//必须设置为false，否则广点通广告无法正常播放（跳转到详情页再返回到信息流页后，视频无法正常播放，同时点击其它广告日志提示点击过快）
        builder.setEnableUserControl(true);//必须设置为true，否则用户点击视频播放器无反应（不会播放视频）

        this.nativeUnifiedADData.bindMediaView(gdtMediaView, builder.build(), new GDTNativeAdMediaListenerImpl(nativeRecyclerAdMediaListener));
//        gdtMediaView.addView(mediaView);//nativeUnifiedADData.bindMediaView()会删除gdtMediaView的孩子，但上面的清理依赖此父子关系，故强制加上此关系
    }

    @Override
    public String getIconUrl() {
        return nativeUnifiedADData.getIconUrl();
    }

    @Override
    public String[] getImgUrls() {
        int adPatternType = this.nativeUnifiedADData.getAdPatternType();
        //广点通逻辑：两图（logo图片和展示图片）两文字（标题和内容）类型
        // 视频类型
        //一图两文字：一个logo图片、标题、内容
        //三图两文字：三个展示图片、标题、内容
        String[] imgUrls = null;
        //两图两文字和视频类型，要从广点通的imgUrl中取图片
        if (adPatternType == AdPatternType.NATIVE_2IMAGE_2TEXT
                || adPatternType == AdPatternType.NATIVE_VIDEO) {
            imgUrls = new String[1];
            imgUrls[0] = this.nativeUnifiedADData.getImgUrl();
        } else if (adPatternType == AdPatternType.NATIVE_3IMAGE) {
            List<String> imgList = this.nativeUnifiedADData.getImgList();
            if (imgList != null) {
                imgUrls = imgList.toArray(new String[0]);
            }
        } else {
        }
        return imgUrls;
    }

    @Override
    public String getTitle() {
        return nativeUnifiedADData.getTitle();
    }

    @Override
    public String getDesc() {
        return nativeUnifiedADData.getDesc();
    }

    @Override
    public void destroy() {
        if (this.nativeUnifiedADData != null) {
            this.nativeUnifiedADData.destroy();
        }
    }

    public GDTNativeUnifiedAdWrapper getAdWrapper() {
        return adWrapper;
    }

    public boolean isHasExposed() {
        return hasExposed;
    }

    protected void setHasExposed(boolean hasExposed) {
        this.hasExposed = hasExposed;
    }
}
