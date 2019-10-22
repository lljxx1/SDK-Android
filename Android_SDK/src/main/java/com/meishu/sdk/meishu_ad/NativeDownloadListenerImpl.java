package com.meishu.sdk.meishu_ad;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.meishu.sdk.service.ClickHandler;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class NativeDownloadListenerImpl implements NativeDownloadListener {
    private static final String TAG = "NativeDownloadListenerI";
    private NativeAd nativeAd;

    public NativeDownloadListenerImpl(@NonNull NativeAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    @Override
    public void onDownloadStart() {
        String[] dn_starts = this.nativeAd.getAdSlot().getDn_start();
        if (dn_starts != null) {
            for (String dn_start : dn_starts) {
                if (!TextUtils.isEmpty(dn_start)) {
                    HttpUtil.asyncGetWithWebViewUA(this.nativeAd.getAdView().getContext(), ClickHandler.replaceMacros(dn_start,nativeAd), new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        Log.d(TAG, "onDownloaded: 下载开始");
    }

    @Override
    public void onDownloaded() {
        String[] dn_succs = this.nativeAd.getAdSlot().getDn_succ();
        if (dn_succs != null) {
            for (String dn_succ : dn_succs) {
                if (!TextUtils.isEmpty(dn_succ)) {
                    HttpUtil.asyncGetWithWebViewUA(this.nativeAd.getAdView().getContext(), ClickHandler.replaceMacros(dn_succ,nativeAd), new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        Log.d(TAG, "onDownloaded: 下载完成");
    }

    @Override
    public void onInstallStart() {
        String[] dn_inst_starts = this.nativeAd.getAdSlot().getDn_inst_start();
        if (dn_inst_starts != null) {
            for (String dn_inst_start : dn_inst_starts) {
                if (!TextUtils.isEmpty(dn_inst_start)) {
                    HttpUtil.asyncGetWithWebViewUA(this.nativeAd.getAdView().getContext(), ClickHandler.replaceMacros(dn_inst_start,nativeAd), new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        Log.d(TAG, "onInstallStart: 开始安装");
    }

    @Override
    public void onDownloadFailed() {
        Log.d(TAG, "onDownloadFailed: 下载失败");
    }
}
