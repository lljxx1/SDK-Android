package com.meishu.sdk.reward.gdt;

import android.support.annotation.NonNull;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.BaseSdkAdWrapper;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.reward.SdkAdListenerWrapper;
import com.meishu.sdk.reward.RewardAdInteractionListener;
import com.meishu.sdk.reward.RewardAdMediaListener;
import com.meishu.sdk.reward.RewardVideoLoader;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;
import com.qq.e.ads.rewardvideo.RewardVideoAD;

public class GDTRewardVideoAdWrapper extends BaseSdkAdWrapper {

    private RewardVideoAD rewardVideoAd;
    private RewardVideoLoader adLoader;
    private RewardAdInteractionListener apiInteractionListener;
    private RewardAdMediaListener apiRewardAdMediaListener;

    public GDTRewardVideoAdWrapper(@NonNull RewardVideoLoader adLoader, @NonNull SdkAdInfo sdkAdInfo) {
        super(adLoader.getActivity(),sdkAdInfo);
        this.adLoader=adLoader;
        this.rewardVideoAd = new RewardVideoAD(adLoader.getActivity(), sdkAdInfo.getApp_id(), sdkAdInfo.getPid(), new RewardVideoAdListenerAdapter(this, new SdkAdListenerWrapper(this,adLoader.getApiAdListener())));
    }

    @Override
    public void loadAd() {
        HttpUtil.asyncGetWithWebViewUA(this.adLoader.getActivity(), this.getSdkAdInfo().getReq(), new DefaultHttpGetWithNoHandlerCallback());
        this.rewardVideoAd.loadAD();
    }

    @Override
    public AdLoader getAdLoader() {
        return this.adLoader;
    }

    public void showAd() {
        this.rewardVideoAd.showAD();
    }

    public boolean hasShown() {
        return this.rewardVideoAd.hasShown();
    }

    public RewardAdInteractionListener getApiInteractionListener() {
        return apiInteractionListener;
    }

    public void setApiInteractionListener(RewardAdInteractionListener apiInteractionListener) {
        this.apiInteractionListener = apiInteractionListener;
    }

    public RewardAdMediaListener getApiRewardAdMediaListener() {
        return apiRewardAdMediaListener;
    }

    public void setApiRewardAdMediaListener(RewardAdMediaListener apiRewardAdMediaListener) {
        this.apiRewardAdMediaListener = apiRewardAdMediaListener;
    }
}
