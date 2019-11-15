package com.meishu.sdk.meishu_ad.splash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class MeishuSplashRootView extends RelativeLayout {

    private AdListener adListener;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.adListener != null) {
            this.adListener.onADExposure();
        }
    }

    public void setAdListener(AdListener adListener) {
        this.adListener = adListener;
    }

    public MeishuSplashRootView(Context context) {
        super(context);
    }

    public MeishuSplashRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeishuSplashRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
