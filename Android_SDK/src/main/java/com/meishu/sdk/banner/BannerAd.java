package com.meishu.sdk.banner;

import android.view.View;

import com.meishu.sdk.AdData;

public interface BannerAd extends AdData {
    /**
     * 获取广告view
     *
     * @return
     */
    View getAdView();

    void setInteractionListener(BannerInteractionListener listener);

    BannerInteractionListener getInteractionListener();
}
