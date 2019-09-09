package com.meishu.sdk.meishu_ad;

import android.util.Log;

public class NativeDownloadListenerImpl implements NativeDownloadListener {
    private static final String TAG = "NativeDownloadListenerI";
    private String reportUrlOnDownloadStart;
    private String reportUrlOnDownloaded;
    private String reportUrlOnInstallStart;

    @Override
    public void onDownloadStart() {
        Log.d(TAG, "onDownloadStart: 下载开始");
    }

    @Override
    public void onDownloaded() {
        Log.d(TAG, "onDownloaded: 下载完成");
    }

    @Override
    public void onInstallStart() {
        Log.d(TAG, "onInstallStart: 开始安装");
    }
}
