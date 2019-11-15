package com.meishu.sdk.splash.meishu;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.TouchAdContainer;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.meishu_ad.splash.AdListener;
import com.meishu.sdk.meishu_ad.splash.NativeSplashAd;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class SplashAdListenerAdapter implements AdListener {
    private com.meishu.sdk.splash.SplashAdListener splashAdListener;
    private MeishuAdNativeWrapper adWrapper;
    private volatile boolean hasExposed;

    public SplashAdListenerAdapter(MeishuAdNativeWrapper adWrapper, @NonNull SplashAdListener splashAdListener) {
        this.adWrapper = adWrapper;
        this.splashAdListener = splashAdListener;
    }

    @Override
    public void onLoaded(NativeSplashAd splashAd) {
        View adView =splashAd.getAdView();
        if(adView!=null){
            MeishuSplashAdAdapter meishuSplashAd=new MeishuSplashAdAdapter(splashAd);

            ViewGroup parent = (ViewGroup) adView.getParent();
            if(parent!=null){
                parent.removeView(adView);
            }
            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
            touchContainer.setTouchPositionListener(new TouchPositionListener(meishuSplashAd));
            touchContainer.addView(adView);
            if(parent!=null){
                parent.addView(touchContainer);
            }
            adView=touchContainer;

            meishuSplashAd.setAdView(adView);
            splashAdListener.onLoaded(meishuSplashAd);
        }
    }

    @Override
    public void onADExposure() {
        if(!hasExposed){
            String[] monitorUrls = this.adWrapper.getAdSlot().getMonitorUrl();
            if (monitorUrls != null) {
                for (String monitorUrl : monitorUrls) {
                    if (!TextUtils.isEmpty(monitorUrl)) {
                        HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), monitorUrl, new DefaultHttpGetWithNoHandlerCallback());
                    }
                }
            }
            splashAdListener.onAdExposure();
            hasExposed=true;
        }

    }

    @Override
    public void onADClosed() {
        splashAdListener.onAdClosed();
    }
}
