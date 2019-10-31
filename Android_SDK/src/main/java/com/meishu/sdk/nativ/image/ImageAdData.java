package com.meishu.sdk.nativ.image;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface ImageAdData {

    String getTitle();

    String getDesc();

    String getIconUrl();

    int getAdPatternType();

    String[] getImgList();

    void bindAdToView(Activity activity, ViewGroup adContainer, List<View> clickableViews, ImageAdInteractionListener imageAdInteractionListener);

    void destroy();

    int getInteractionType();
}
