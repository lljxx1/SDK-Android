package com.meishu.sdk.config;

public class GDTAdConfig {
    private String appId;

    private GDTAdConfig() {
    }

    private static GDTAdConfig instance;
    public static GDTAdConfig getInstance(){
        if(instance==null){
            instance=new GDTAdConfig();
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
