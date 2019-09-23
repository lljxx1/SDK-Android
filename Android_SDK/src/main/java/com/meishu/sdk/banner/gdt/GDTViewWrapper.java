package com.meishu.sdk.banner.gdt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.banner.BannerAdListener;
import com.meishu.sdk.banner.BannerAdLoader;
import com.meishu.sdk.banner.SdkAdListenerWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.BannerView;

public class GDTViewWrapper extends BaseSdkAdWrapper {

    private BannerView gdtBannerView;
    private BannerAdLoader adLoader;

    public GDTViewWrapper(@NonNull BannerAdLoader adLoader, @NonNull SdkAdInfo sdkAdInfo, @Nullable BannerAdListener bannerADListener) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        gdtBannerView = new BannerView(adLoader.getActivity(), ADSize.BANNER, sdkAdInfo.getApp_id(), sdkAdInfo.getPid());
        if (bannerADListener != null) {
            gdtBannerView.setADListener(new GDTBannerAdListenerImpl(this, new SdkAdListenerWrapper(this, bannerADListener)));
        }
    }

    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        gdtBannerView.loadAD();
    }

    public View getAdView() {
        return gdtBannerView;
    }

    @Override
    public void destroy() {
        gdtBannerView.destroy();
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }
}
