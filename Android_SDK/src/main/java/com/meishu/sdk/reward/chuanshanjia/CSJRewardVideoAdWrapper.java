package com.meishu.sdk.reward.chuanshanjia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Surface;
import android.view.WindowManager;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.reward.RewardVideoAdListener;
import com.meishu.sdk.reward.SdkAdListenerWrapper;
import com.meishu.sdk.reward.RewardAdMediaListener;
import com.meishu.sdk.reward.RewardVideoLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class CSJRewardVideoAdWrapper extends BaseSdkAdWrapper {

    private SdkAdInfo sdkAdInfo;
    private TTAdNative mTTAdNative;
    private RewardVideoLoader adLoader;
    private RewardAdMediaListener apiRewardAdMediaListener;
    private RewardVideoAdListener sdkRewardAdListener;
    private MeishuAdInfo meishuAdInfo;

    public CSJRewardVideoAdWrapper(@NonNull RewardVideoLoader adLoader, @NonNull SdkAdInfo sdkAdInfo,@NonNull MeishuAdInfo meishuAdInfo) {
        super(adLoader.getActivity(), sdkAdInfo);
        this.adLoader = adLoader;
        this.sdkAdInfo = sdkAdInfo;
        this.mTTAdNative = TTAdSdk.getAdManager().createAdNative(adLoader.getActivity());
        this.meishuAdInfo=meishuAdInfo;
    }

    @Override
    public void loadAd() {
        int rotation = ((WindowManager) this.adLoader.getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        int orientation = TTAdConstant.VERTICAL;
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                orientation = TTAdConstant.VERTICAL;
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                orientation = TTAdConstant.HORIZONTAL;
                break;
        }
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(this.sdkAdInfo.getPid())
                .setSupportDeepLink(true)
                .setImageAcceptedSize(meishuAdInfo.getWidth(), meishuAdInfo.getHeight())
                //必传参数，表来标识应用侧唯一用户；若非服务器回调模式或不需sdk透传
                //可设置为空字符串
                .setUserID("")
                .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                .build();

        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        this.sdkRewardAdListener=new SdkAdListenerWrapper(this,this.adLoader.getApiAdListener());
        mTTAdNative.loadRewardVideoAd(adSlot, new RewardVideoListenerAdapter(this, this.sdkRewardAdListener));
    }

    @Override
    public RewardVideoLoader getAdLoader() {
        return this.adLoader;
    }

    public RewardAdMediaListener getApiRewardAdMediaListener() {
        return apiRewardAdMediaListener;
    }

    public RewardVideoAdListener getSdkRewardAdListener() {
        return sdkRewardAdListener;
    }

    public void setApiRewardAdMediaListener(RewardAdMediaListener apiRewardAdMediaListener) {
        this.apiRewardAdMediaListener = apiRewardAdMediaListener;
    }
}
