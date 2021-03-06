package com.meishu.sdk.meishu_ad.splash;

import android.view.ViewGroup;

import com.meishu.sdk.service.AdSlot;

public class SplashAdSlot implements AdSlot {

    private String appId;
    private String posId;

    private String[] imageUrl;
    private int interactionType;
    private ViewGroup adContainer;
    private String[] dUrl;
    private String appName;
    private String deep_link;
    private String[] monitorUrl;
    private String[] clickUrl;
    private String[] dn_start;
    private String[] dn_succ;
    private String[] dn_inst_start;
    private String[] dp_start;
    private String[] dp_fail;
    private String clickid;

    public class Builder {
        public Builder() {
        }

        public Builder setAppId(String appId) {
            SplashAdSlot.this.appId = appId;
            return this;
        }

        public Builder setPosId(String posId) {
            SplashAdSlot.this.posId = posId;
            return this;
        }

        public Builder setImageUrls(String[] imageUrls) {
            SplashAdSlot.this.imageUrl = imageUrls;
            return this;
        }

        public Builder setInteractionType(int interactionType) {
            SplashAdSlot.this.interactionType = interactionType;
            return this;
        }

        public Builder setAdContainer(ViewGroup adContainer) {
            SplashAdSlot.this.adContainer = adContainer;
            return this;
        }

        public Builder setDUrl(String[] dUrl) {
            SplashAdSlot.this.dUrl = dUrl;
            return this;
        }

        public Builder setAppName(String appName) {
            SplashAdSlot.this.appName = appName;
            return this;
        }

        public Builder setDeepLink(String deepLink) {
            SplashAdSlot.this.deep_link = deepLink;
            return this;
        }

        public SplashAdSlot.Builder setMonitorUrl(String[] monitorUrl) {
            SplashAdSlot.this.monitorUrl = monitorUrl;
            return this;
        }

        public SplashAdSlot.Builder setClickUrl(String[] clickUrl) {
            SplashAdSlot.this.clickUrl = clickUrl;
            return this;
        }

        public Builder setDn_start(String[] dn_start) {
            SplashAdSlot.this.dn_start = dn_start;
            return this;
        }

        public Builder setDn_succ(String[] dn_succ) {
            SplashAdSlot.this.dn_succ = dn_succ;
            return this;
        }

        public Builder setDn_inst_start(String[] dn_inst_start) {
            SplashAdSlot.this.dn_inst_start = dn_inst_start;
            return this;
        }

        public Builder setDp_start(String[] dp_start) {
            SplashAdSlot.this.dp_start = dp_start;
            return this;
        }

        public Builder setDp_fail(String[] dp_fail) {
            SplashAdSlot.this.dp_fail = dp_fail;
            return this;
        }

        public Builder setClickid(String clickid) {
            SplashAdSlot.this.clickid = clickid;
            return this;
        }

        public SplashAdSlot build() {
            return SplashAdSlot.this;
        }
    }

    public String getAppId() {
        return appId;
    }

    public String getPosId() {
        return posId;
    }

    public String[] getImageUrls() {
        return imageUrl;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public ViewGroup getAdContainer() {
        return adContainer;
    }

    @Override
    public String getDeep_link() {
        return this.deep_link;
    }

    @Override
    public String[] getDn_start() {
        return dn_start;
    }

    @Override
    public String[] getDn_succ() {
        return dn_succ;
    }

    @Override
    public String[] getDn_inst_start() {
        return dn_inst_start;
    }

    @Override
    public String[] getDp_start() {
        return this.dp_start;
    }

    @Override
    public String[] getDp_fail() {
        return this.dp_fail;
    }

    public String[] getdUrl() {
        return dUrl;
    }

    @Override
    public void setdUrl(String[] dUrls) {
        this.dUrl = dUrls;
    }

    public String getAppName() {
        return appName;
    }

    @Override
    public String[] getMonitorUrl() {
        return this.monitorUrl;
    }

    @Override
    public String[] getClickUrl() {
        return this.clickUrl;
    }

    @Override
    public String getClickid() {
        return clickid;
    }
}
