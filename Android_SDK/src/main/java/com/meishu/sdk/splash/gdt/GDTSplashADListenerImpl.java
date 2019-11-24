package com.meishu.sdk.splash.gdt;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.comm.util.AdError;

public class GDTSplashADListenerImpl implements com.qq.e.ads.splash.SplashADListener {
    private static final String TAG = "GDTSplashADListenerImpl";
    private SplashAdListener splashADListener;

    private GDTSplashAdWrapper splashAdWrapper;
//    private volatile boolean onClosedMethodInvoked;

    public GDTSplashADListenerImpl(@NonNull GDTSplashAdWrapper splashAdWrapper, SplashAdListener splashADListener) {
        this.splashAdWrapper = splashAdWrapper;
        this.splashADListener = splashADListener;
    }

    private GDTSplashAd splashAd;

    @Override
    public void onADClicked() {
        if (this.splashAdWrapper.getSdkAdInfo() != null) {
            HttpUtil.asyncGetWithWebViewUA(
                    this.splashAdWrapper.getView().getContext(),
                    ClickHandler.replaceOtherMacros(this.splashAdWrapper.getSdkAdInfo().getClk(), this.splashAd),
                    new DefaultHttpGetWithNoHandlerCallback()
            );
        }
        if (splashAd != null && splashAd.getInteractionListener() != null) {
            splashAd.getInteractionListener().onAdClicked();
        }
    }

    @Override
    public void onADExposure() {
        if (splashADListener != null) {
            splashADListener.onAdExposure();
        }
    }

    @Override
    public void onADTick(long leftMiliseconds) {
        long leftSeconds = leftMiliseconds / 1000;
        Log.d(TAG, "onADTick: 剩余" + leftSeconds + "秒");
//        if (leftSeconds <= 0 && splashADListener != null && !this.onClosedMethodInvoked) {
//            this.onClosedMethodInvoked = true;
//        }
    }

    @Override
    public void onADDismissed() {
        Log.d(TAG, "onADDismissed: ");

        splashADListener.onAdClosed();
    }

    @Override
    public void onNoAD(AdError adError) {
        if (this.splashADListener != null) {
            this.splashADListener.onError();
        }
    }

    @Override
    public void onADPresent() {
        if (splashAdWrapper != null && splashAdWrapper.getView() != null) {
            splashAd = new GDTSplashAd();
            View adView = splashAdWrapper.getView();

            //广点通开屏广告不允许给adView添加TouchAdContainer，否则会导致开屏广告无法正常显示
//            ViewGroup parent = (ViewGroup) adView.getParent();
//            if(parent!=null){
//                parent.removeView(adView);
//            }
//            TouchAdContainer touchContainer = new TouchAdContainer(adView.getContext());
//            touchContainer.setTouchPositionListener(new TouchPositionListener(splashAd));
//            touchContainer.addView(adView);
//            if(parent!=null){
//                parent.addView(touchContainer);
//            }
//            adView=touchContainer;

            splashAd.setAdView(adView);

            splashADListener.onLoaded(splashAd);
        }
    }
}
