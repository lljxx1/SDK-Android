package com.meishu.sdk.nativ.recycler.meishu;

import android.text.TextUtils;

import com.meishu.sdk.meishu_ad.nativ.AdListener;
import com.meishu.sdk.nativ.recycler.NativeAdData;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class MeishuAdListenerAdapter implements AdListener {
    private NativeAdListener apiAdListener;
    private MeishuAdNativeWrapper adWrapper;

    public MeishuAdListenerAdapter(MeishuAdNativeWrapper adWrapper, NativeAdListener apiAdListener) {
        this.adWrapper = adWrapper;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(List<com.meishu.sdk.meishu_ad.nativ.NativeAdData> adDatas) {
        if (this.apiAdListener != null && adDatas != null) {
            List<NativeAdData> apiAdDatas = new ArrayList<>();
            for (com.meishu.sdk.meishu_ad.nativ.NativeAdData adData : adDatas) {
                apiAdDatas.add(new MeishuNativeAdDataAdapter(this.adWrapper, adData));
            }
            this.apiAdListener.onAdLoaded(apiAdDatas);
        }
    }

    @Override
    public void onADExposure() {
        String[] monitorUrls = this.adWrapper.getAdSlot().getMonitorUrl();
        if (monitorUrls != null) {
            for (String monitorUrl : monitorUrls) {
                if (!TextUtils.isEmpty(monitorUrl)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), monitorUrl, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdExposure();
        }
    }

    @Override
    public void onAdError() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
