package com.meishu.sdk.nativ.recycler.meishu;

import android.text.TextUtils;

import com.meishu.sdk.meishu_ad.nativ.AdListener;
import com.meishu.sdk.nativ.recycler.RecyclerAdData;
import com.meishu.sdk.nativ.recycler.RecyclerAdListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class MeishuAdListenerAdapter implements AdListener {
    private RecyclerAdListener apiAdListener;
    private MeishuAdNativeWrapper adWrapper;
    private volatile boolean hasExposed;

    public MeishuAdListenerAdapter(MeishuAdNativeWrapper adWrapper, RecyclerAdListener apiAdListener) {
        this.adWrapper = adWrapper;
        this.apiAdListener = apiAdListener;
    }

    @Override
    public void onAdLoaded(List<com.meishu.sdk.meishu_ad.nativ.NativeAdData> adDatas) {
        if (this.apiAdListener != null && adDatas != null) {
            List<RecyclerAdData> apiAdDatas = new ArrayList<>();
            for (com.meishu.sdk.meishu_ad.nativ.NativeAdData adData : adDatas) {
                apiAdDatas.add(new MeishuRecyclerAdDataAdapter(this.adWrapper, adData));
            }
            this.apiAdListener.onAdLoaded(apiAdDatas);
        }
    }

    @Override
    public void onADExposure() {
        if (!hasExposed) {
            hasExposed = true;
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

    }

    @Override
    public void onAdError() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }
}
