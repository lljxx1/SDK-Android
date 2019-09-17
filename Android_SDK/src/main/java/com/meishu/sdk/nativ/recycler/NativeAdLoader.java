package com.meishu.sdk.nativ.recycler;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.meishu.sdk.AdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.nativ.recycler.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.nativ.recycler.gdt.GDTNativeUnifiedAdWrapper;
import com.meishu.sdk.nativ.recycler.meishu.MeishuAdNativeWrapper;
import com.meishu.sdk.service.RequestUtil;
import com.meishu.sdk.utils.HttpGetJsonCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.meishu.sdk.utils.OriginalResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class NativeAdLoader implements NativeAdDelegate {
    private static final String TAG = "NativeAdLoader";
    private NativeAdDelegate nativeADDelegate;
    private Activity activity;
    private String posId;
    private NativeAdListener adListener;

    public NativeAdLoader(Activity activity, String posId, NativeAdListener listener) {
        this.activity = activity;
        this.posId = posId;
        this.adListener = listener;
    }

    @Override
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
                    if (NativeAdLoader.this.adListener != null) {
                        NativeAdLoader.this.adListener.onAdError();
                    }
                } else if (httpResponse.isSuccessful()) {
                    String responseType = httpResponse.header(MeishuConstants.response_type_key);
                    Gson jsonUtil = new Gson();
                    if (MeishuConstants.response_type_meishu.equals(responseType)) {//要求加载美数广告
                        MeishuAdInfo meishuAdInfo = jsonUtil.fromJson(httpResponse.getBody(), MeishuAdInfo.class);

                        NativeAdSlot adSlot = new NativeAdSlot().new Builder()
                                .setAppId(MeishuAdConfig.getInstance().getAppId())
                                .setPosId(meishuAdInfo.getPid())
                                .setTitle(meishuAdInfo.getTitle())
                                .setDesc(meishuAdInfo.getContent())
                                .setInteractionType(meishuAdInfo.getTarget_type())
                                .setWidth(meishuAdInfo.getWidth())
                                .setHeight(meishuAdInfo.getHeight())
                                .setDUrl(meishuAdInfo.getdUrl())
                                .setAppName(meishuAdInfo.getApp_name())
                                .setDeepLink(meishuAdInfo.getDeep_link())
                                .setMonitorUrl(meishuAdInfo.getMonitorUrl())
                                .setClickUrl(meishuAdInfo.getClickUrl())
                                .setDp_start(meishuAdInfo.getDp_start())
                                .setDp_fail(meishuAdInfo.getDp_fail())
                                .setImageUrl(meishuAdInfo.getSrcUrls())
                                .setVideo_cover(meishuAdInfo.getVideo_cover())
                                .build();
                        int creativeType = 1;
                        if (meishuAdInfo.getCreative_type() != null) {
                            creativeType = meishuAdInfo.getCreative_type();
                        }
                        adSlot.setAdPatternType(creativeType);
                        if (creativeType == 1) {
                            adSlot.setImageUrls(meishuAdInfo.getSrcUrls());
                            nativeADDelegate = new MeishuAdNativeWrapper(activity, adSlot, NativeAdLoader.this.adListener);
                        } else if (creativeType == 2) {
                            if (meishuAdInfo.getSrcUrls() != null && meishuAdInfo.getSrcUrls().length > 0) {
                                adSlot.setVideoUrl(meishuAdInfo.getSrcUrls()[0]);
                            }
                            nativeADDelegate = new MeishuAdNativeWrapper(activity, adSlot, NativeAdLoader.this.adListener);
                        } else {
                            Log.e(TAG, "", new Exception("不支持的创意类型，类型标识为[" + creativeType + "]"));
                        }
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
                            nativeADDelegate = (NativeAdDelegate) delegateChain;
                            if (delegateChain != null) {
                                DelegateChain current = delegateChain;
                                for (int i = 1; i < sdkCount; i++) {
                                    current.setNext(createDelegate(sdks.get(i)));
                                    current = current.getNext();
                                }
                            }
                        }

                    }
                    if (nativeADDelegate != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nativeADDelegate.loadAd();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "onResponse: ", new Exception("广告请求出错，错误码[" + httpResponse.getBody() + "]"));
                }
            }
        });
    }

    private DelegateChain createDelegate(SdkAdInfo sdkAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTNativeUnifiedAdWrapper(activity, sdkAdInfo, NativeAdLoader.this.adListener);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(activity, sdkAdInfo, NativeAdLoader.this.adListener);
        } else {
            delegate = null;
        }
        return delegate;
    }
}
