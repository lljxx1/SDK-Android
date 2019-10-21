package com.meishu.sdk.banner.chuanshanjia;

import android.support.annotation.NonNull;
import android.view.View;

import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.banner.BannerInteractionListener;
import com.meishu.sdk.service.ClickHandler;
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
            TouchPositionListener.TouchPosition touchPosition = csjBannerAd.getTouchPosition();

            HttpUtil.asyncGetWithWebViewUA(
                    this.csjBannerAd.getAdView().getContext(),
                    ClickHandler.replaceMacros(
                            this.csjBannerAd.getSdkAdInfo().getClk(),
                            this.csjBannerAd
                    ),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        interactionListener.onAdClicked();
    }

    @Override
    public void onAdShow(View view, int i) {
        interactionListener.onAdClicked();
    }
}
