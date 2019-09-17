package com.meishu.sdk.meishu_ad.nativ;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.NativeAd;

import java.util.List;

public interface NativeAdData extends NativeAd {

    int getAdPatternType();

    int getInteractionType();

    void bindAdToView(Activity activity, ViewGroup container, List<View> clickableViews, NativeAdInteractionListener adInteractionListener);

    void bindMediaView(ViewGroup mediaView, NativeAdMediaListener nativeAdMediaListener);

    String getIconUrl();

    String[] getImgUrls();

    String getTitle();

    String getDesc();

    NativeAdSlot getAdSlot();

    MediaView getMediaView();
}
