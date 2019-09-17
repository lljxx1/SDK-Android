package com.meishu.sdk.reward.chuanshanjia;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Surface;
import android.view.WindowManager;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.reward.RewardVideoAdDelegate;
import com.meishu.sdk.reward.RewardVideoAdListener;

public class CSJRewardVideoAdWrapper implements RewardVideoAdDelegate, DelegateChain {

    private Activity activity;
    private SdkAdInfo sdkAdInfo;
    private RewardVideoAdListener adListener;
    private TTAdNative mTTAdNative;
    private DelegateChain next;

    public CSJRewardVideoAdWrapper(@NonNull Activity activity, @NonNull SdkAdInfo sdkAdInfo, @NonNull RewardVideoAdListener adListener) {
        this.activity = activity;
        this.sdkAdInfo = sdkAdInfo;
        this.adListener = adListener;
        this.mTTAdNative = TTAdSdk.getAdManager().createAdNative(activity);
    }

    @Override
    public void loadAd() {
        int rotation = ((WindowManager) this.activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
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
                .setImageAcceptedSize(1080, 1920)
                //必传参数，表来标识应用侧唯一用户；若非服务器回调模式或不需sdk透传
                //可设置为空字符串
                .setUserID("")
                .setOrientation(orientation) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                .build();
        mTTAdNative.loadRewardVideoAd(adSlot, new RewardVideoListenerAdapter(this,this.adListener));
    }


    @Override
    public void setNext(DelegateChain next) {
        this.next = next;
    }

    @Override
    public DelegateChain getNext() {
        return this.next;
    }

    @Override
    public SdkAdInfo getSdkAdInfo() {
        return this.sdkAdInfo;
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }

}
