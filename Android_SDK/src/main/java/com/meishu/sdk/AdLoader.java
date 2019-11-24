package com.meishu.sdk;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.meishu.sdk.domain.ClickIdResponse;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.service.RequestUtil;
import com.meishu.sdk.utils.HttpGetJsonCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.meishu.sdk.utils.OriginalResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class AdLoader {
    private static final String TAG = "AdLoader";
    private Activity activity;
    private String posId;

    public AdLoader(Activity activity, String posId) {
        this.activity = activity;
        this.posId = posId;
    }

    public void loadAd() {

        Map<String, String> params = RequestUtil.wrapParams(activity, this.posId);

        HttpUtil.asyncGetJson(MeishuConstants.ad_request_url, params, new HttpGetJsonCallback<OriginalResponse>() {
            @Override
            public void onFailure(@NotNull IOException e) {
                Log.e(TAG, "onFailure: ", e);
                handleNoAd();
            }

            @Override
            public void onResponse(OriginalResponse httpResponse) throws IOException {
                if (httpResponse.getCode() == 204) {
                    handleNoAd();
                } else if (httpResponse.isSuccessful()) {
                    final Gson jsonUtil = new Gson();
                    final MeishuAdInfo meishuAdInfo = jsonUtil.fromJson(httpResponse.getBody(), MeishuAdInfo.class);
                    if (meishuAdInfo.getTarget_type() != null &&
                            meishuAdInfo.getTarget_type() == 1 &&
                            meishuAdInfo.getFrom() != null &&
                            meishuAdInfo.getFrom().equalsIgnoreCase("GDT")
                    ) {
                        HttpUtil.asyncGetJson(meishuAdInfo.getdUrl()[0], null, new HttpGetJsonCallback<OriginalResponse>() {
                            @Override
                            public void onFailure(@NotNull IOException e) {
                                Log.e(TAG, "onFailure: ", e);
                                handleNoAd();
                            }

                            @Override
                            public void onResponse(OriginalResponse dUrlResponse) throws IOException {
                                boolean hasError = true;
                                try {
                                    if (dUrlResponse.isSuccessful()) {
                                        ClickIdResponse clickIdResponse = jsonUtil.fromJson(dUrlResponse.getBody(), ClickIdResponse.class);
                                        meishuAdInfo.setClickid(clickIdResponse.getData().getClickid());
                                        meishuAdInfo.setdUrl(new String[]{clickIdResponse.getData().getDstlink()});
                                        loadAd(meishuAdInfo);
                                        hasError = false;
                                    } else {
                                        Log.d(TAG, "onResponse: 从dUrl请求clickId失败");
                                    }
                                } catch (Exception e) {
                                    Log.e(TAG, "onResponse: ", e);
                                }
                                if (hasError) {
                                    handleNoAd();
                                }
                            }
                        });
                    } else {
                        loadAd(meishuAdInfo);
                    }
                } else {
                    handleNoAd();
                }
            }
        });
    }

    private void loadAd(MeishuAdInfo meishuAdInfo) {
        boolean hasMeishuAd = false;
        if (meishuAdInfo.getMonitorUrl() != null && meishuAdInfo.getMonitorUrl().length > 0) {//美数返回的json中可能只包括sdk，不包含美数广告内容，因此要判断是否包含美数广告内容
            hasMeishuAd = true;
        }
        DelegateChain prior = null;
        DelegateChain tail = null;
        SdkAdInfo[] sdks = meishuAdInfo.getSdk();
        sdks = removeUnsupportedSdk(sdks);
        if (sdks != null && sdks.length > 0) {//要求加载其他厂商广告
            AdSdk.InitSdkConfigIfNoInit(activity, sdks[0]);
            DelegateChain delegateChain = createDelegate(sdks[0], meishuAdInfo);
            prior = tail = delegateChain;
            if (delegateChain != null) {
                DelegateChain current = delegateChain;
                for (int i = 1; i < sdks.length; i++) {
                    AdSdk.InitSdkConfigIfNoInit(activity, sdks[i]);
                    current.setNext(createDelegate(sdks[i], meishuAdInfo));
                    current = current.getNext();
                    tail = current;
                }
            }
        }
        if (hasMeishuAd) {
            //若美数返回的广告内容中包含美数广告，则把美数广告作为哨兵元素
            //当存在sdk广告时优先加载sdk广告，若不存在sdk广告或sdk加载广告失败，则加载美数广告
            DelegateChain meishuAdDelegate = createMeishuAdDelegate(AdLoader.this.activity, meishuAdInfo);

            if (tail != null) {
                tail.setNext(meishuAdDelegate);
            }
            tail = meishuAdDelegate;

            if (prior == null) {
                prior = tail;
            }
        }
        if (prior != null) {
            final DelegateChain first = prior;
            AdLoader.this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    first.loadAd();
                }
            });
        }
    }

    protected abstract DelegateChain createMeishuAdDelegate(Activity activity, MeishuAdInfo meishuAdInfo);

    protected abstract DelegateChain createDelegate(SdkAdInfo sdkAdInfo, MeishuAdInfo meishuAdInfo);

    protected abstract void handleNoAd();

    public Activity getActivity() {
        return activity;
    }

    public String getPosId() {
        return posId;
    }

    private AdDelegate current;

    public void destroy() {
        if (this.current != null) {
            this.current.destroy();
        }
    }

    public void setCurrent(AdDelegate current) {
        this.current = current;
    }

    private SdkAdInfo[] removeUnsupportedSdk(SdkAdInfo[] originalSdks) {
        SdkAdInfo[] filteredSdksArray = null;
        if (originalSdks != null) {
            ArrayList<SdkAdInfo> filteredSdks = new ArrayList<>(originalSdks.length);
            for (int i = 0; i < originalSdks.length; i++) {
                if (!TextUtils.isEmpty(originalSdks[i].getSdk())
                        && (
                        originalSdks[i].getSdk().equalsIgnoreCase("GDT")
                                || originalSdks[i].getSdk().equalsIgnoreCase("CSJ")
                )
                        && testGdtSdkHasExternalStoragePermission(originalSdks[i].getSdk())
                ) {
                    filteredSdks.add(originalSdks[i]);
                }
            }
            filteredSdksArray = filteredSdks.toArray(new SdkAdInfo[0]);
        }
        return filteredSdksArray;
    }

    private boolean testGdtSdkHasExternalStoragePermission(String sdk) {
        if (!"GDT".equalsIgnoreCase(sdk)) {//非广点通，不用判断权限
            return true;
        }
        if ("GDT".equalsIgnoreCase(sdk)
                && (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        ) {
            //广点通需要判断存储权限，若没有存储权限，程序无法正常运行
            return true;
        } else {
            return false;
        }

    }
}
