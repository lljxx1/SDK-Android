package com.meishu.sdk.meishu_ad.nativ;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NativeAdDataImpl implements NativeAdData {
    private int adPatternType;
    private String iconUrl;
    private String[] imgUrls;
    private String title;
    private String desc;

    private MediaView mediaView;
    private NativeAdSlot nativeAdSlot;

    public NativeAdDataImpl(@NonNull NativeAdSlot nativeAdSlot) {
        this.nativeAdSlot = nativeAdSlot;
    }

    @Override
    public int getAdPatternType() {
        return this.adPatternType;
    }

    @Override
    public int getInteractionType() {
        return this.nativeAdSlot.getInteractionType();
    }

    @Override
    public void bindAdToView(Activity activity, ViewGroup container, List<View> clickableViews, final NativeAdInteractionListener adInteractionListener) {
        if (clickableViews != null) {
            for (View clickableView : clickableViews) {
                clickableView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (adInteractionListener != null) {
                            adInteractionListener.onAdClicked();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void bindMediaView(ViewGroup mediaView, NativeAdMediaListener nativeAdMediaListener) {
        MediaView nativeMediaView = this.getMediaView();
        if (nativeMediaView != null) {
            nativeMediaView.setNativeAdMediaListener(nativeAdMediaListener);
            ViewGroup mediaViewContainer = (ViewGroup) nativeMediaView.getParent();

            if (mediaViewContainer == null) {
                mediaView.addView(nativeMediaView);
            }
        }

    }

    @Override
    public String getIconUrl() {
        return this.iconUrl;
    }

    @Override
    public String[] getImgUrls() {
        return this.imgUrls;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public NativeAdSlot getAdSlot() {
        return this.nativeAdSlot;
    }

    @Override
    public View getAdView() {
        return getMediaView();
    }

    @Override
    public MediaView getMediaView() {
        return this.mediaView;
    }

    public class Builder {
        public Builder setAdPatternType(int adPatternType) {
            NativeAdDataImpl.this.adPatternType = adPatternType;
            return this;
        }

        public Builder setIconUrl(String iconUrl) {
            NativeAdDataImpl.this.iconUrl = iconUrl;
            return this;
        }

        public Builder setImgUrls(String[] imgUrls) {
            NativeAdDataImpl.this.imgUrls = imgUrls;
            return this;
        }

        public Builder setTitle(String title) {
            NativeAdDataImpl.this.title = title;
            return this;
        }

        public Builder setDesc(String desc) {
            NativeAdDataImpl.this.desc = desc;
            return this;
        }

        public Builder setMediaView(MediaView mediaView) {
            NativeAdDataImpl.this.mediaView = mediaView;
            return this;
        }

        public NativeAdDataImpl build() {
            return NativeAdDataImpl.this;
        }
    }

}
