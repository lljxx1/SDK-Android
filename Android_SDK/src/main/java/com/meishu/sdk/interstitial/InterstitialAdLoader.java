package com.meishu.sdk.interstitial;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.meishu.sdk.AdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdSlot;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.interstitial.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.interstitial.gdt.GDTInterstitialAdWrapper;
import com.meishu.sdk.interstitial.meishu.MeishuAdNativeWrapper;
import com.meishu.sdk.service.RequestUtil;
import com.meishu.sdk.utils.HttpGetJsonCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.meishu.sdk.utils.OriginalResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class InterstitialAdLoader implements InterstitialAdDelegate {
    private static final String TAG = "InterstitialAdLoader";

    private Activity activity;
    private InterstitialAdListener apiAdListener;
    private String posId;

    private InterstitialAdDelegate interstitialADDelegate;

    public InterstitialAdLoader(@NonNull Activity activity, String posId, InterstitialAdListener adListener) {
        this.activity = activity;
        this.apiAdListener = adListener;
        this.posId = posId;
    }

    public void loadAd() {
        Map<String, String> params = RequestUtil.wrapParams(activity, this.posId);

        HttpUtil.asyncGetJson(MeishuConstants.ad_request_url, params, new HttpGetJsonCallback<OriginalResponse>() {
            @Override
            public void onFailure(@NotNull IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(OriginalResponse httpResponse) throws IOException {
                if (httpResponse.getCode() == 204) {
                    Log.d(TAG, "onResponse: 没有广告");
                } else if (httpResponse.isSuccessful()) {
                    String responseType = httpResponse.header(MeishuConstants.response_type_key);
                    Gson jsonUtil = new Gson();
                    if (MeishuConstants.response_type_meishu.equals(responseType)) {//要求加载美数广告
                        MeishuAdInfo meishuAdInfo = jsonUtil.fromJson(httpResponse.getBody(), MeishuAdInfo.class);

                        InterstitialAdSlot adSlot = new InterstitialAdSlot().new Builder()
                                .setAppId(MeishuAdConfig.getInstance().getAppId())
                                .setPosId(meishuAdInfo.getPid())
                                .setImageUrls(meishuAdInfo.getSrcUrls())
                                .setInteractionType(meishuAdInfo.getTarget_type())
                                .setWidth(meishuAdInfo.getWidth())
                                .setHeight(meishuAdInfo.getHeight())
                                .setDUrl(meishuAdInfo.getdUrl())
                                .setAppName(meishuAdInfo.getApp_name())
                                .setDeepLink(meishuAdInfo.getDeep_link())
                                .setMonitorUrl(meishuAdInfo.getMonitorUrl())
                                .setClickUrl(meishuAdInfo.getClickUrl())
                                .setDn_start(meishuAdInfo.getDn_start())
                                .setDn_succ(meishuAdInfo.getDn_succ())
                                .setDn_inst_start(meishuAdInfo.getDn_inst_start())
                                .setDp_start(meishuAdInfo.getDp_start())
                                .setDp_fail(meishuAdInfo.getDp_fail())
                                .build();
                        interstitialADDelegate = new MeishuAdNativeWrapper(activity, adSlot, apiAdListener);
                    } else {//要求加载其他厂商广告

                        JsonParser jsonParser = new JsonParser();
                        JsonArray jsonElements = jsonParser.parse(httpResponse.getBody()).getAsJsonArray();
                        ArrayList<SdkAdInfo> sdks = new ArrayList<>();
                        int sdkCount = 0;
                        for (JsonElement element : jsonElements) {
                            SdkAdInfo sdkAdInfo = jsonUtil.fromJson(element, SdkAdInfo.class);
                            AdSdk.InitSdkConfigIfNoInit(activity, sdkAdInfo);
                            sdks.add(sdkAdInfo);
                            sdkCount++;
                        }
                        if (sdkCount > 0) {
                            DelegateChain delegateChain = createDelegate(sdks.get(0));
                            interstitialADDelegate = (InterstitialAdDelegate) delegateChain;
                            if (delegateChain != null) {
                                DelegateChain current = delegateChain;
                                for (int i = 1; i < sdkCount; i++) {
                                    current.setNext(createDelegate(sdks.get(i)));
                                    current = current.getNext();
                                }
                            }
                        }
                    }
                    if (interstitialADDelegate != null) {
                        interstitialADDelegate.loadAd();
                    }
                } else {
                    Log.e(TAG, "onResponse: ", new Exception("广告请求出错，错误码[" + httpResponse.getBody() + "]"));
                }
            }
        });
    }

    public void destroy() {
        if (interstitialADDelegate != null) {
            interstitialADDelegate.destroy();
        }
    }

    private DelegateChain createDelegate(SdkAdInfo sdkAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTInterstitialAdWrapper(activity, sdkAdInfo, apiAdListener);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(activity, sdkAdInfo, apiAdListener);
        } else {
            delegate = null;
        }
        return delegate;
    }

}
