package com.meishu.sdk.banner;

import android.view.View;

public interface BannerAd {
    /**
     * 获取广告view
     *
     * @return
     */
    View getAdView();

    void setInteractionListener(BannerInteractionListener listener);

    BannerInteractionListener getInteractionListener();
}
