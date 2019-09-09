package com.meishu.sdk.nativ.image;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface NativeAdData {

    String getTitle();

    String getDesc();

    String getIconUrl();

    int getAdPatternType();

    String[] getImgList();

    void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, AdInteractionListener adInteractionListener);

    void destroy();

    int getInteractionType();
}
