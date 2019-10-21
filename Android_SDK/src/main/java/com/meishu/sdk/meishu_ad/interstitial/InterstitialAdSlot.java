package com.meishu.sdk.meishu_ad.interstitial;

import com.meishu.sdk.service.AdSlot;

public class InterstitialAdSlot implements AdSlot {

    private String appId;
    private String posId;

    private String[] imageUrls;
    private int interactionType;
    private int width;
    private int height;
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

    public class Builder {

        public Builder() {
        }

        public Builder setAppId(String appId) {
            InterstitialAdSlot.this.appId = appId;
            return this;
        }

        public Builder setPosId(String posId) {
            InterstitialAdSlot.this.posId = posId;
            return this;
        }

        public Builder setImageUrls(String[] imageUrls) {
            InterstitialAdSlot.this.imageUrls = imageUrls;
            return this;
        }

        public Builder setInteractionType(int interactionType) {
            InterstitialAdSlot.this.interactionType = interactionType;
            return this;
        }

        public Builder setWidth(int width) {
            InterstitialAdSlot.this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            InterstitialAdSlot.this.height = height;
            return this;
        }

        public Builder setDUrl(String[] dUrl) {
            InterstitialAdSlot.this.dUrl = dUrl;
            return this;
        }

        public Builder setAppName(String appName) {
            InterstitialAdSlot.this.appName = appName;
            return this;
        }

        public Builder setDeepLink(String deep_link) {
            InterstitialAdSlot.this.deep_link = deep_link;
            return this;
        }

        public InterstitialAdSlot.Builder setMonitorUrl(String[] monitorUrl) {
            InterstitialAdSlot.this.monitorUrl = monitorUrl;
            return this;
        }

        public InterstitialAdSlot.Builder setClickUrl(String[] clickUrl) {
            InterstitialAdSlot.this.clickUrl = clickUrl;
            return this;
        }

        public InterstitialAdSlot.Builder setDn_start(String[] dn_start) {
            InterstitialAdSlot.this.dn_start = dn_start;
            return this;
        }

        public InterstitialAdSlot.Builder setDn_succ(String[] dn_succ) {
            InterstitialAdSlot.this.dn_succ = dn_succ;
            return this;
        }

        public InterstitialAdSlot.Builder setDn_inst_start(String[] dn_inst_start) {
            InterstitialAdSlot.this.dn_inst_start = dn_inst_start;
            return this;
        }

        public Builder setDp_start(String[] dp_start) {
            InterstitialAdSlot.this.dp_start = dp_start;
            return this;
        }

        public Builder setDp_fail(String[] dp_fail) {
            InterstitialAdSlot.this.dp_fail = dp_fail;
            return this;
        }

        public InterstitialAdSlot build() {
            return InterstitialAdSlot.this;
        }
    }

    public String getAppId() {
        return appId;
    }

    public String getPosId() {
        return posId;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public int getInteractionType() {
        return interactionType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String[] getdUrl() {
        return dUrl;
    }

    @Override
    public void setdUrl(String[] dUrls) {
        this.dUrl=dUrls;
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
    public String getDeep_link() {
        return deep_link;
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
        return dp_start;
    }

    @Override
    public String[] getDp_fail() {
        return dp_fail;
    }
}
