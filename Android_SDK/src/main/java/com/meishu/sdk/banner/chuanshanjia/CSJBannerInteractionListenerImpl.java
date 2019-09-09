package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJBannerInteractionListenerImpl implements TTBannerAd.AdInteractionListener {

    private BannerInteractionListener interactionListener;
    private CSJBannerAd csjBannerAd;

    public CSJBannerInteractionListenerImpl(CSJBannerAd csjBannerAd, @NonNull BannerInteractionListener interactionListener) {
        this.csjBannerAd = csjBannerAd;
        this.interactionListener = interactionListener;
    }

    @Override
    public void onAdClicked(View view, int i) {
        if (this.csjBannerAd.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(this.csjBannerAd.getAdView().getContext(), this.csjBannerAd.getSdkAdInfo().getClk(), new DefaultHttpGetWithNoHandlerCallback());
        }
        interactionListener.onAdClicked();
    }

    @Override
    public void onAdShow(View view, int i) {
        interactionListener.onAdClicked();
    }
}
