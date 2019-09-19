package com.meishu.sdk.meishu_ad.banner;

import com.meishu.sdk.service.AdSlot;

public class BannerAdSlot implements AdSlot {

    private String appId;
    private String posId;

    private String[] imageUrls;
    private int interactionType;
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
            BannerAdSlot.this.appId = appId;
            return this;
        }

        public Builder setPosId(String posId) {
            BannerAdSlot.this.posId = posId;
            return this;
        }

        public Builder setImageUrls(String[] imageUrls) {
            BannerAdSlot.this.imageUrls = imageUrls;
            return this;
        }

        public Builder setInteractionType(int interactionType) {
            BannerAdSlot.this.interactionType = interactionType;
            return this;
        }

        public Builder setDUrl(String[] dUrl) {
            BannerAdSlot.this.dUrl = dUrl;
            return this;
        }

        public Builder setAppName(String appName) {
            BannerAdSlot.this.appName = appName;
            return this;
        }

        public Builder setDeepLink(String deepLink) {
            BannerAdSlot.this.deep_link = deepLink;
            return this;
        }

        public Builder setMonitorUrl(String[] monitorUrl) {
            BannerAdSlot.this.monitorUrl = monitorUrl;
            return this;
        }

        public Builder setClickUrl(String[] clickUrl) {
            BannerAdSlot.this.clickUrl = clickUrl;
            return this;
        }

        public Builder setDn_start(String[] dn_start) {
            BannerAdSlot.this.dn_start = dn_start;
            return this;
        }

        public Builder setDn_succ(String[] dn_succ) {
            BannerAdSlot.this.dn_succ = dn_succ;
            return this;
        }

        public Builder setDn_inst_start(String[] dn_inst_start) {
            BannerAdSlot.this.dn_inst_start = dn_inst_start;
            return this;
        }

        public Builder setDp_start(String[] dp_start) {
            BannerAdSlot.this.dp_start = dp_start;
            return this;
        }

        public Builder setDp_fail(String[] dp_fail) {
            BannerAdSlot.this.dp_fail = dp_fail;
            return this;
        }

        public BannerAdSlot build() {
            return BannerAdSlot.this;
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

    public String[] getdUrl() {
        return dUrl;
    }

    public String getAppName() {
        return appName;
    }

    public String getDeep_link() {
        return deep_link;
    }

    @Override
    public String[] getMonitorUrl() {
        return monitorUrl;
    }

    @Override
    public String[] getClickUrl() {
        return clickUrl;
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

    public String[] getDp_start() {
        return dp_start;
    }

    @Override
    public String[] getDp_fail() {
        return dp_fail;
    }


}
