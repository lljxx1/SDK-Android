package com.meishu.sdk;

public final class MeishuConstants {
    public static String ad_request_url;

    public final static String response_type_meishu = "API";
    public final static String getResponse_type_others = "SDK";
    public final static String response_type_key = "Response_type";

    /**
     * 交互类型：网页跳转类广告
     */
    public final static int interactionType_url = 0;
    /**
     * 交互类型：下载类广告
     */
    public final static int interactionType_download = 1;
    /**
     * 初始化appId时，开发者使用到的map对应的key值
     */
    public final static String appIdKey_meishu = "MEISHU";
    /**
     * 初始化appId时，开发者使用到的map对应的key值
     */
    public final static String appIdKey_gdt = "GDT";
    /**
     * 初始化appId时，开发者使用到的map对应的key值
     */
    public final static String appIdKey_csj = "CSJ";

}
