package com.meishu.sdk.meishu_ad.nativ;

import com.meishu.sdk.service.AdSlot;

public class NativeAdSlot implements AdSlot {

    private String appId;
    private String posId;

    private int width;
    private int height;
    /**
     * 预加载广告个数
     */
    private int fetchCount = 1;

    private String title;
    private String desc;
    /**
     * 创意类型
     */
    private int adPatternType;
    /**
     * 交互类型
     */
    private int interactionType;
    private String iconUrl;
    private String[] imageUrls;
    private String videoUrl;

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
    private String[] video_start;
    private String[] video_one_quarter;
    private String[] video_one_half;
    private String[] video_three_quarter;
    private String[] video_complete;
    private String[] video_pause;
    private String[] video_mute;
    private String[] video_unmute;
    private String[] video_replay;
    private String video_cover;

    public class Builder {

        public Builder() {
        }

        public Builder setAppId(String appId) {
            NativeAdSlot.this.appId = appId;
            return this;
        }

        public Builder setPosId(String posId) {
            NativeAdSlot.this.posId = posId;
            return this;
        }

        public Builder setInteractionType(int interactionType) {
            NativeAdSlot.this.interactionType = interactionType;
            return this;
        }

        public Builder setWidth(int width) {
            NativeAdSlot.this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            NativeAdSlot.this.height = height;
            return this;
        }

        private Builder setfetchCount(int fetchCount) {
            NativeAdSlot.this.fetchCount = fetchCount;
            return this;
        }

        public Builder setTitle(String title) {
            NativeAdSlot.this.title = title;
            return this;
        }

        public Builder setDesc(String desc) {
            NativeAdSlot.this.desc = desc;
            return this;
        }

        public Builder setAdPatternType(int adPatternType) {
            NativeAdSlot.this.adPatternType = adPatternType;
            return this;
        }

        public Builder setIconUrl(String iconUrl) {
            NativeAdSlot.this.iconUrl = iconUrl;
            return this;
        }

        public Builder setImageUrl(String[] imageUrls) {
            NativeAdSlot.this.imageUrls = imageUrls;
            return this;
        }

        public Builder setVideoUrl(String videoUrl) {
            NativeAdSlot.this.videoUrl = videoUrl;
            return this;
        }

        public Builder setDUrl(String[] dUrl) {
            NativeAdSlot.this.dUrl = dUrl;
            return this;
        }

        public Builder setAppName(String appName) {
            NativeAdSlot.this.appName = appName;
            return this;
        }

        public Builder setDeepLink(String deepLink) {
            NativeAdSlot.this.deep_link = deepLink;
            return this;
        }

        public NativeAdSlot.Builder setMonitorUrl(String[] monitorUrl) {
            NativeAdSlot.this.monitorUrl = monitorUrl;
            return this;
        }

        public NativeAdSlot.Builder setClickUrl(String[] clickUrl) {
            NativeAdSlot.this.clickUrl = clickUrl;
            return this;
        }

        public NativeAdSlot.Builder setDn_start(String[] dn_start) {
            NativeAdSlot.this.dn_start = dn_start;
            return this;
        }

        public NativeAdSlot.Builder setDn_succ(String[] dn_succ) {
            NativeAdSlot.this.dn_succ = dn_succ;
            return this;
        }

        public NativeAdSlot.Builder setDn_inst_start(String[] dn_inst_start) {
            NativeAdSlot.this.dn_inst_start = dn_inst_start;
            return this;
        }

        public Builder setDp_start(String[] dp_start) {
            NativeAdSlot.this.dp_start = dp_start;
            return this;
        }


        public Builder setDp_fail(String[] dp_fail) {
            NativeAdSlot.this.dp_fail = dp_fail;
            return this;
        }

        public Builder setVideo_start(String[] video_start) {
            NativeAdSlot.this.video_start = video_start;
            return this;
        }

        public Builder setVideo_one_quarter(String[] video_one_quarter) {
            NativeAdSlot.this.video_one_quarter = video_one_quarter;
            return this;
        }
        public Builder setVideo_one_half(String[] video_one_half) {
            NativeAdSlot.this.video_one_half = video_one_half;
            return this;
        }
        public Builder setVideo_three_quarter(String[] video_three_quarter) {
            NativeAdSlot.this.video_three_quarter = video_three_quarter;
            return this;
        }
        public Builder setVideo_complete(String[] video_complete) {
            NativeAdSlot.this.video_complete = video_complete;
            return this;
        }
        public Builder setVideo_pause(String[] video_pause) {
            NativeAdSlot.this.video_pause = video_pause;
            return this;
        }
        public Builder setVideo_mute(String[] video_mute) {
            NativeAdSlot.this.video_mute = video_mute;
            return this;
        }
        public Builder setVideo_unmute(String[] video_unmute) {
            NativeAdSlot.this.video_unmute = video_unmute;
            return this;
        }
        public Builder setVideo_replay(String[] video_replay) {
            NativeAdSlot.this.video_replay = video_replay;
            return this;
        }

        public Builder setVideo_cover(String video_cover) {
            NativeAdSlot.this.video_cover = video_cover;
            return this;
        }

        public NativeAdSlot build() {
            return NativeAdSlot.this;
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

    public int getFetchCount() {
        return fetchCount;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getAdPatternType() {
        return adPatternType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setAdPatternType(int adPatternType) {
        this.adPatternType = adPatternType;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public String[] getVideo_start() {
        return video_start;
    }

    public String[] getVideo_one_quarter() {
        return video_one_quarter;
    }

    public String[] getVideo_one_half() {
        return video_one_half;
    }

    public String[] getVideo_three_quarter() {
        return video_three_quarter;
    }

    public String[] getVideo_complete() {
        return video_complete;
    }

    public String[] getVideo_pause() {
        return video_pause;
    }

    public String[] getVideo_mute() {
        return video_mute;
    }

    public String[] getVideo_unmute() {
        return video_unmute;
    }

    public String[] getVideo_replay() {
        return video_replay;
    }

    public String getVideo_cover() {
        return video_cover;
    }

}
