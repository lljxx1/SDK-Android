package com.meishu.sdk;

public interface DownloadListener {
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
     * 成功安装下载的应用
     */
//    void onInstalled();

    /**
     * 成功打开下载的应用
     */
//    void onOpened();
}
