package com.meishu.sdk.meishu_ad;

public interface NativeDownloadListener {
    /**
     * 开始下载应用
     */
    void onDownloadStart();

    /**
     * 成功下载应用
     */
    void onDownloaded();

    /**
     * 开始安装应用
     */
    void onInstallStart();


    /**
     * 下载应用失败
     */
    void onDownloadFailed();
}
