package com.meishu.sdk.splash;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.meishu.sdk.AdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.meishu_ad.splash.SplashAdSlot;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.service.RequestUtil;
import com.meishu.sdk.splash.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.splash.gdt.GDTSplashAdWrapper;
import com.meishu.sdk.splash.meishu.MeishuAdNativeWrapper;
import com.meishu.sdk.utils.HttpGetJsonCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.meishu.sdk.utils.OriginalResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
 * <p>
 * 在调用SDK之前，如果您的App的targetSDKVersion >= 23，
 * 那么一定要把"READ_PHONE_STATE"、"WRITE_EXTERNAL_STORAGE"、
 * "ACCESS_FINE_LOCATION"这几个权限申请到，否则SDK将不会工作。
 */
public class SplashAdLoader {
    private static final String TAG = "SplashAdLoader";
    private SplashAdDelegate splashADDelegate;
    private Activity activity;
    private ViewGroup adContainer;
    private String posId;
    private SplashAdListener adListener;
    private int fetchDelay;

    public SplashAdLoader(Activity activity, ViewGroup adContainer, String posId, SplashAdListener adListener, int fetchDelay) {
        this.activity = activity;
        this.adContainer = adContainer;
        this.posId = posId;
        this.adListener = adListener;
        this.fetchDelay = fetchDelay;
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

                        SplashAdSlot adSlot = new SplashAdSlot().new Builder()
                                .setAppId(MeishuAdConfig.getInstance().getAppId())
                                .setPosId(meishuAdInfo.getPid())
                                .setImageUrls(meishuAdInfo.getSrcUrls())
                                .setInteractionType(meishuAdInfo.getTarget_type())
                                .setAdContainer(SplashAdLoader.this.adContainer)
                                .setDUrl(meishuAdInfo.getdUrl())
                                .setAppName(meishuAdInfo.getApp_name())
                                .setDeepLink(meishuAdInfo.getDeep_link())
                                .setMonitorUrl(meishuAdInfo.getMonitorUrl())
                                .setClickUrl(meishuAdInfo.getClickUrl())
                                .setDp_start(meishuAdInfo.getDp_start())
                                .setDp_fail(meishuAdInfo.getDp_fail())
                                .build();
                        splashADDelegate = new MeishuAdNativeWrapper(activity, adSlot, adListener);
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
                            splashADDelegate = (SplashAdDelegate) delegateChain;
                            if (delegateChain != null) {
                                DelegateChain current = delegateChain;
                                for (int i = 1; i < sdkCount; i++) {
                                    current.setNext(createDelegate(sdks.get(i)));
                                    current = current.getNext();
                                }
                            }
                        }
                    }
                    if (splashADDelegate != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            splashADDelegate.loadAd();
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
            delegate = new GDTSplashAdWrapper(activity, adContainer, sdkAdInfo, adListener, fetchDelay);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(activity, adContainer, sdkAdInfo, adListener, fetchDelay);
        } else {
            delegate = null;
        }
        return delegate;
    }
}