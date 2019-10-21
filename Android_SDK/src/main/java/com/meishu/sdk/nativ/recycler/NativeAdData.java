package com.meishu.sdk.nativ.recycler;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface NativeAdData{

    int getAdPatternType();

    int getInteractionType();

    void bindAdToView(Activity activity, ViewGroup container, List<View> clickableViews, AdInteractionListener adInteractionListener);

    void bindMediaView(ViewGroup mediaView, AdMediaListener nativeAdMediaListener);

    String getIconUrl();

    String[] getImgUrls();

    String getTitle();

    String getDesc();

    void destroy();
}
