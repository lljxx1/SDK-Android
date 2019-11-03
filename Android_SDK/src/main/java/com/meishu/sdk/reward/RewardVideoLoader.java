package com.meishu.sdk.reward;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.reward.chuanshanjia.CSJRewardVideoAdWrapper;
import com.meishu.sdk.reward.gdt.GDTRewardVideoAdWrapper;
import com.meishu.sdk.reward.meishu.MeishuRewardVideoAdWrapper;

public class RewardVideoLoader extends AdLoader {
    private static final String TAG = "RewardVideoLoader";
    private RewardVideoAdListener apiAdListener;

    public RewardVideoLoader(Activity activity, String posId, RewardVideoAdListener apiAdListener) {
        super(activity, posId);
        this.apiAdListener = apiAdListener;
    }

    @Override
    protected DelegateChain createMeishuAdDelegate(Activity activity, @NonNull MeishuAdInfo meishuAdInfo) {
        NativeAdSlot adSlot = new NativeAdSlot().new Builder()
                .setAppId(MeishuAdConfig.getInstance().getAppId())
                .setPosId(meishuAdInfo.getPid())
                .setTitle(meishuAdInfo.getTitle())
                .setDesc(meishuAdInfo.getContent())
                .setInteractionType(meishuAdInfo.getTarget_type())
                .setWidth(meishuAdInfo.getWidth())
                .setHeight(meishuAdInfo.getHeight())
                .setDUrl(meishuAdInfo.getdUrl())
                .setVideo_cover(meishuAdInfo.getVideo_cover())
                .setVideo_endcover(meishuAdInfo.getVideo_endcover())
                .setVideo_keep_time(meishuAdInfo.getVideo_keep_time())
                .setAppName(meishuAdInfo.getApp_name())
                .setDeepLink(meishuAdInfo.getDeep_link())
                .setMonitorUrl(meishuAdInfo.getMonitorUrl())
                .setClickUrl(meishuAdInfo.getClickUrl())
                .setDn_start(meishuAdInfo.getDn_start())
                .setDn_succ(meishuAdInfo.getDn_succ())
                .setDn_inst_start(meishuAdInfo.getDn_inst_start())
                .setDp_start(meishuAdInfo.getDp_start())
                .setDp_fail(meishuAdInfo.getDp_fail())
                .setVideo_start(meishuAdInfo.getVideo_start())
                .setVideo_one_quarter(meishuAdInfo.getVideo_one_quarter())
                .setVideo_one_half(meishuAdInfo.getVideo_one_half())
                .setVideo_three_quarter(meishuAdInfo.getVideo_three_quarter())
                .setVideo_complete(meishuAdInfo.getVideo_complete())
                .setVideo_pause(meishuAdInfo.getVideo_pause())
                .setVideo_mute(meishuAdInfo.getVideo_mute())
                .setVideo_unmute(meishuAdInfo.getVideo_unmute())
                .setVideo_replay(meishuAdInfo.getVideo_replay())
                .setImageUrl(meishuAdInfo.getSrcUrls())
                .setClickid(meishuAdInfo.getClickid())
                .build();
        int creativeType = 1;

        if (meishuAdInfo.getCreative_type() != null) {
            creativeType = meishuAdInfo.getCreative_type();
        }
        adSlot.setAdPatternType(creativeType);
        DelegateChain adDelegate = null;
        if (creativeType == 1) {
            adSlot.setImageUrls(meishuAdInfo.getSrcUrls());
            adDelegate = new MeishuRewardVideoAdWrapper(this, adSlot);
        } else if (creativeType == 2) {
            if (meishuAdInfo.getSrcUrls() != null && meishuAdInfo.getSrcUrls().length > 0) {
                adSlot.setVideoUrl(meishuAdInfo.getSrcUrls()[0]);
            }
            adDelegate = new MeishuRewardVideoAdWrapper(this, adSlot);
        } else {
            Log.e(TAG, "", new Exception("不支持的创意类型，类型标识为[" + creativeType + "]"));
        }
        return adDelegate;
    }

    @Override
    protected DelegateChain createDelegate(SdkAdInfo sdkAdInfo,@NonNull MeishuAdInfo meishuAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTRewardVideoAdWrapper(this, sdkAdInfo);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJRewardVideoAdWrapper(this, sdkAdInfo,meishuAdInfo);
        } else {
            delegate = null;
        }
        return delegate;
    }

    @Override
    protected void handleNoAd() {
        if (this.apiAdListener != null) {
            this.apiAdListener.onAdError();
        }
    }

    public RewardVideoAdListener getApiAdListener() {
        return apiAdListener;
    }
}
