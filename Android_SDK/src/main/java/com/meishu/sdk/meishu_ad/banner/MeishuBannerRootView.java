package com.meishu.sdk.meishu_ad.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MeishuBannerRootView extends RelativeLayout {

private AdListener adListener;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.adListener!=null){
            this.adListener.onADExposure();
        }
    }

    public void setAdListener(AdListener adListener) {
        this.adListener = adListener;
    }

    public MeishuBannerRootView(Context context) {
        super(context);
    }

    public MeishuBannerRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeishuBannerRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
