package com.meishu.sdk.config;

public class MeishuAdConfig {
    private String appId;

    private MeishuAdConfig() {
    }

    private static MeishuAdConfig instance;

    public static MeishuAdConfig getInstance() {
        if (instance == null) {
            instance = new MeishuAdConfig();
        }
        return instance;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
