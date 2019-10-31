package com.meishu.sdk.nativ.image.meishu;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.nativ.image.ImageAdInteractionListener;
import com.meishu.sdk.nativ.image.ImageAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdData;

import java.util.List;

public class MeishuImageAdDataAdapter implements ImageAdData {
    private RecyclerAdData internalAdData;

    public MeishuImageAdDataAdapter(RecyclerAdData internalAdData) {
        this.internalAdData = internalAdData;
    }

    @Override
    public String getTitle() {
        String title = null;
        if (this.internalAdData != null) {
            title = this.internalAdData.getTitle();
        }
        return title;
    }

    @Override
    public String getDesc() {
        String desc = null;
        if (this.internalAdData != null) {
            desc = this.internalAdData.getDesc();
        }
        return desc;
    }

    @Override
    public String getIconUrl() {
        String iconUrl = null;
        if (this.internalAdData != null) {
            iconUrl = this.internalAdData.getIconUrl();
        }
        return iconUrl;
    }

    @Override
    public int getAdPatternType() {
        int adPatternType = -9999;
        if (this.internalAdData != null) {
            adPatternType = this.internalAdData.getAdPatternType();
        }
        return adPatternType;
    }

    @Override
    public String[] getImgList() {
        String[] imgList = null;
        if (this.internalAdData != null) {
            imgList = this.internalAdData.getImgUrls();
        }
        return imgList;
    }

    @Override
    public void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, ImageAdInteractionListener imageAdInteractionListener) {
        if (internalAdData != null) {
            internalAdData.bindAdToView(activity, adContainer, clickableViews, new NativeRecylcerAdInteractionListenerAdapter(imageAdInteractionListener));
        }
    }

    @Override
    public void destroy() {
        if (internalAdData != null) {
            internalAdData.destroy();
        }
    }

    @Override
    public int getInteractionType() {
        return internalAdData.getInteractionType();
    }
}
