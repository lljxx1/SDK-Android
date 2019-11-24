package com.meishu.sdk.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.meishu.sdk.AdData;
import com.meishu.sdk.MeishuConstants;
import com.meishu.sdk.TouchPositionListener;
import com.meishu.sdk.activity.AlertDialogActivity;
import com.meishu.sdk.activity.WebviewActivity;
import com.meishu.sdk.meishu_ad.NativeAd;
import com.meishu.sdk.meishu_ad.NativeDownloadListenerImpl;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.DownloadUtils;
import com.meishu.sdk.utils.HttpUtil;

public class ClickHandler {
    private static final String TAG = "ClickHandler";

    public static String replaceOtherMacros(@NonNull String url, AdData adData) {
        TouchPositionListener.TouchPosition touchPosition = adData.getTouchPosition();
        long currentTime = System.currentTimeMillis();
        return TextUtils.replace(url, new String[]{
                "__DOWN_X__",
                "__DOWN_Y__",
                "__UP_X__",
                "__UP_Y__",
                "__MS_EVENT_SEC__",
                "__MS_EVENT_MSEC__"
        }, new String[]{
                String.valueOf(touchPosition == null ? -999 : touchPosition.getDownX()),
                String.valueOf(touchPosition == null ? -999 : touchPosition.getDownY()),
                String.valueOf(touchPosition == null ? -999 : touchPosition.getUpX()),
                String.valueOf(touchPosition == null ? -999 : touchPosition.getUpY()),
                String.valueOf(touchPosition == null ? currentTime / 1000 : touchPosition.getTouchTime().getTime() / 1000),
                String.valueOf(touchPosition == null ? currentTime : touchPosition.getTouchTime().getTime())
        }).toString();
    }

    public static String replaceMacros(@NonNull String url, NativeAd nativeAd) {
        AdSlot adSlot = nativeAd.getAdSlot();
        String replaceOtherMacros = replaceOtherMacros(url, nativeAd);
        return TextUtils.replace(
                replaceOtherMacros,
                new String[]{"__CLICK_ID__"},
                new String[]{
                        TextUtils.isEmpty(adSlot.getClickid()) ? "__CLICK_ID__" : adSlot.getClickid()
                }
        ).toString();
    }

    public static void handleClick(final NativeAd nativeAd) {
        AdSlot adSlot = nativeAd.getAdSlot();
        String[] clickUrls = adSlot.getClickUrl();
        if (clickUrls != null) {
            for (String clickUrl : clickUrls) {
                if (!TextUtils.isEmpty(clickUrl)) {
                    HttpUtil.asyncGetWithWebViewUA(nativeAd.getAdView().getContext(), replaceMacros(clickUrl, nativeAd), new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        String[] dUrls = adSlot.getdUrl();
        if (dUrls != null) {
            String[] handledDUrls = new String[dUrls.length];
            int i = 0;
            for (String dUrl : dUrls) {
                handledDUrls[i++] = replaceMacros(dUrl, nativeAd);
            }
            adSlot.setdUrl(handledDUrls);
        }
        final Context context = nativeAd.getAdView().getContext();
        if (!TextUtils.isEmpty(adSlot.getDeep_link())) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(adSlot.getDeep_link());
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (isInstall(context, intent)) {
                String urls[] = adSlot.getDp_start();
                if (urls != null) {
                    for (String dp_start : urls) {
                        if (!TextUtils.isEmpty(dp_start)) {
                            HttpUtil.asyncGetWithWebViewUA(context, replaceMacros(dp_start, nativeAd), new DefaultHttpGetWithNoHandlerCallback());
                        }
                    }
                }
                context.startActivity(intent);
            } else {//程序未安装，要转到落地页
                if (nativeAd.getAdSlot().getdUrl() != null && nativeAd.getAdSlot().getdUrl().length > 0) {
                    Intent deeplink_fail_intent = new Intent(context, WebviewActivity.class);
                    deeplink_fail_intent.putExtra(WebviewActivity.urlIntent, nativeAd.getAdSlot().getdUrl());
                    context.startActivity(deeplink_fail_intent);
                }

//                download(nativeAd);
                /*String urls[] = adSlot.getDp_fail();
                if (urls != null) {
                    for (String dp_fail : urls) {
                        if (!TextUtils.isEmpty(dp_fail)) {
                            HttpUtil.asyncGetWithWebViewUA(context, dp_fail, new DefaultHttpGetWithNoHandlerCallback());
                        }
                    }
                    Log.d(TAG, "onAdClicked: 应用未安装");
                }*/
            }
        } else if (nativeAd.getInteractionType() == MeishuConstants.interactionType_download) {
            if (isInternetConnected(context) && !isWifiConnected(context)) {//流量网络要让用户确认下载
                AlertDialogActivity.setConfirmHandler(new AlertDialogActivity.ConfirmHandler() {
                    @Override
                    public void handle() {
                        download(nativeAd);
                    }
                });
                context.startActivity(new Intent(context,AlertDialogActivity.class));
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//                alertBuilder.setTitle("下载");
//                alertBuilder.setMessage("确认要下载吗？");
//                alertBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        download(nativeAd);
//                    }
//                });
//                alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                alertBuilder.create().show();
            } else if (isInternetConnected(context)) {
                download(nativeAd);
            } else {
                Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
            }

        } else if (nativeAd.getInteractionType() == MeishuConstants.interactionType_url) {
            Intent intent = new Intent(context, WebviewActivity.class);
            intent.putExtra(WebviewActivity.urlIntent, nativeAd.getAdSlot().getdUrl());
            context.startActivity(intent);
        } else {

        }
    }

    //判断app是否安装
    private static boolean isInstall(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    private static void download(NativeAd nativeAd) {
        AdSlot adSlot = nativeAd.getAdSlot();
        if (adSlot.getdUrl() != null && adSlot.getdUrl().length > 0) {
            DownloadUtils downloadUtils =
                    new DownloadUtils(
                            nativeAd.getAdView().getContext(),
                            adSlot.getdUrl()[0],
                            adSlot.getAppName() + ".apk");
            downloadUtils.setDownloadListener(new NativeDownloadListenerImpl(nativeAd));
            downloadUtils.downloadAPK();
            Toast.makeText(nativeAd.getAdView().getContext(), "开始下载", Toast.LENGTH_SHORT).show();
        }
    }


    private static boolean isWifiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isWifiConnected = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                isWifiConnected = true;
            }
        }
        return isWifiConnected;
    }

    private static boolean isInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isInternetConnected = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isInternetConnected = true;
        }
        return isInternetConnected;
    }
}
