package com.meishu.sdk.meishu_ad.interstitial;

import android.content.Context;
import android.view.View;

import com.meishu.sdk.R;

import razerdp.basepopup.BasePopupWindow;

public class Popup extends BasePopupWindow {
    public Popup(Context context) {
        super(context);
    }

    public Popup(Context context, int width, int height) {
        super(context, width, height);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.interstitial_ad_layout);
    }

    @Override
    public void setViewClickListener(View.OnClickListener listener, View... views) {
        super.setViewClickListener(listener, views);
    }
}
