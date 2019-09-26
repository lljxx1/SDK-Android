package com.meishu.sdk.domain;

public class MeishuAdInfo {
    /**
     * ssp平台广告主id，非必须
     */
    private String ader_id;
    private String app_name;
    /**
     * ssp平台创意id，非必须，保留字段
     */
    private String cid;
    /**
     * 广告被点击时，必须触发上报
     * 必须
     */
    private String[] clickUrl;
    /**
     * 摘要描述
     * 非必须
     */
    private String content;
    /**
     * 创意类型（ 1:图片、 2:视频、 3： 音频） 默认 1
     * 非必须
     */
    private Integer creative_type;
    /**
     * 点击落地页
     * 非必须
     */
    private String [] dUrl;
    /**
     * 下载类广告（安装完成并打开应用时上报）
     * 非必须
     */
    private String[] dn_active;
    /**
     * 下载类广告（安装开始时上报）
     * 非必须
     */
    private String[] dn_inst_start;
    /**
     * 下载类广告（安装成功时上报，广点通广告）
     * 非必须
     */
    private String[] dn_inst_succ;
    /**
     * 下载类广告（下载开始时上报，广点通广告）
     * 非必须
     */
    private String[] dn_start;
    /**
     * 下载类广告（下载成功时上报，广点通广告）
     * 非必须
     */
    private String[] dn_succ;
    /**
     *广告位宽
     * 必须
     */
    private Integer width;
    /**
     *广告位高
     * 必须
     */
    private Integer height;
    /**
     *广告曝光时必须触发上报
     * 必须
     */
    private String[] monitorUrl;
    /**
     *应用包名
     * 非必须
     */
    private String package_name;
    /**
     *广告位id
     * 非必须
     */
    private String pid;
    /**
     *物料 URL
     * 非必须
     */
    private String[] srcUrls;
    /**
     *广告交互类型(0:网页跳转,1:下载) 默认值:0
     * 非必须
     */
    private Integer target_type;
    /**
     *
     * 非必须
     */
    private String title;

    /**
     *
     * 	非必须
     */
    private String deep_link;

    /**
     * deep_link 字段非空时，直接唤起类（尝试 唤起时上报）
     */
    private String[] dp_start;
    /**
     * deep_link 字段非空时，直接唤起类（唤起 失败时上报）
     */
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

    private SdkAdInfo[] sdk;

    public String getAder_id() {
        return ader_id;
    }

    public void setAder_id(String ader_id) {
        this.ader_id = ader_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String[] getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String[] clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreative_type() {
        return creative_type;
    }

    public void setCreative_type(Integer creative_type) {
        this.creative_type = creative_type;
    }

    public String[] getdUrl() {
        return dUrl;
    }

    public void setdUrl(String[] dUrl) {
        this.dUrl = dUrl;
    }

    public String[] getDn_active() {
        return dn_active;
    }

    public void setDn_active(String[] dn_active) {
        this.dn_active = dn_active;
    }

    public String[] getDn_inst_start() {
        return dn_inst_start;
    }

    public void setDn_inst_start(String[] dn_inst_start) {
        this.dn_inst_start = dn_inst_start;
    }

    public String[] getDn_inst_succ() {
        return dn_inst_succ;
    }

    public void setDn_inst_succ(String[] dn_inst_succ) {
        this.dn_inst_succ = dn_inst_succ;
    }

    public String[] getDn_start() {
        return dn_start;
    }

    public void setDn_start(String[] dn_start) {
        this.dn_start = dn_start;
    }

    public String[] getDn_succ() {
        return dn_succ;
    }

    public void setDn_succ(String[] dn_succ) {
        this.dn_succ = dn_succ;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String[] getMonitorUrl() {
        return monitorUrl;
    }

    public void setMonitorUrl(String[] monitorUrl) {
        this.monitorUrl = monitorUrl;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String[] getSrcUrls() {
        return srcUrls;
    }

    public void setSrcUrls(String[] srcUrls) {
        this.srcUrls = srcUrls;
    }

    public Integer getTarget_type() {
        return target_type;
    }

    public void setTarget_type(Integer target_type) {
        this.target_type = target_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeep_link() {
        return deep_link;
    }

    public void setDeep_link(String deep_link) {
        this.deep_link = deep_link;
    }

    public String[] getDp_start() {
        return dp_start;
    }

    public void setDp_start(String[] dp_start) {
        this.dp_start = dp_start;
    }

    public String[] getDp_fail() {
        return dp_fail;
    }

    public void setDp_fail(String[] dp_fail) {
        this.dp_fail = dp_fail;
    }

    public String getVideo_cover() {
        return video_cover;
    }

    public String[] getVideo_start() {
        return video_start;
    }

    public void setVideo_start(String[] video_start) {
        this.video_start = video_start;
    }

    public String[] getVideo_one_quarter() {
        return video_one_quarter;
    }

    public void setVideo_one_quarter(String[] video_one_quarter) {
        this.video_one_quarter = video_one_quarter;
    }

    public String[] getVideo_one_half() {
        return video_one_half;
    }

    public void setVideo_one_half(String[] video_one_half) {
        this.video_one_half = video_one_half;
    }

    public String[] getVideo_three_quarter() {
        return video_three_quarter;
    }

    public void setVideo_three_quarter(String[] video_three_quarter) {
        this.video_three_quarter = video_three_quarter;
    }

    public String[] getVideo_complete() {
        return video_complete;
    }

    public void setVideo_complete(String[] video_complete) {
        this.video_complete = video_complete;
    }

    public String[] getVideo_pause() {
        return video_pause;
    }

    public void setVideo_pause(String[] video_pause) {
        this.video_pause = video_pause;
    }

    public String[] getVideo_mute() {
        return video_mute;
    }

    public void setVideo_mute(String[] video_mute) {
        this.video_mute = video_mute;
    }

    public String[] getVideo_unmute() {
        return video_unmute;
    }

    public void setVideo_unmute(String[] video_unmute) {
        this.video_unmute = video_unmute;
    }

    public String[] getVideo_replay() {
        return video_replay;
    }

    public void setVideo_replay(String[] video_replay) {
        this.video_replay = video_replay;
    }

    public void setVideo_cover(String video_cover) {
        this.video_cover = video_cover;
    }

    public SdkAdInfo[] getSdk() {
        return sdk;
    }

    public void setSdk(SdkAdInfo[] sdk) {
        this.sdk = sdk;
    }
}
