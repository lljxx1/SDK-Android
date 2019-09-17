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
    private String [] imageUrls;
    private String videoUrl;

    private String[] dUrl;
    private String appName;

    private String deep_link;
    private String[] monitorUrl;
    private String[] clickUrl;
    private String[] dp_start;
    private String[] dp_fail;
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
        public Builder setDp_start(String[] dp_start) {
            NativeAdSlot.this.dp_start = dp_start;
            return this;
        }

        public Builder setDp_fail(String[] dp_fail) {
            NativeAdSlot.this.dp_fail = dp_fail;
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
    public String[] getDp_start() {
        return dp_start;
    }

    @Override
    public String[] getDp_fail() {
        return dp_fail;
    }

    public String getVideo_cover() {
        return video_cover;
    }
}
