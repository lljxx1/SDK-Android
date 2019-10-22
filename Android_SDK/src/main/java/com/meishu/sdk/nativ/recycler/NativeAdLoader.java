package com.meishu.sdk.nativ.recycler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.AdLoader;
import com.meishu.sdk.DelegateChain;
import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.domain.MeishuAdInfo;
import com.meishu.sdk.domain.SdkAdInfo;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.nativ.recycler.chuanshanjia.CSJTTAdNativeWrapper;
import com.meishu.sdk.nativ.recycler.gdt.GDTNativeUnifiedAdWrapper;
import com.meishu.sdk.nativ.recycler.meishu.MeishuAdNativeWrapper;

public class NativeAdLoader extends AdLoader {
    private static final String TAG = "NativeAdLoader";
    private NativeAdListener apiAdListener;

    public NativeAdLoader(@NonNull Activity activity, @NonNull String posId, NativeAdListener listener) {
        super(activity, posId);
        this.apiAdListener = listener;
    }

    @Override
    protected DelegateChain createMeishuAdDelegate(Activity activity, MeishuAdInfo meishuAdInfo) {
        NativeAdSlot adSlot = new NativeAdSlot().new Builder()
                .setAppId(MeishuAdConfig.getInstance().getAppId())
                .setPosId(meishuAdInfo.getPid())
                .setTitle(meishuAdInfo.getTitle())
                .setDesc(meishuAdInfo.getContent())
                .setInteractionType(meishuAdInfo.getTarget_type())
                .setWidth(meishuAdInfo.getWidth())
                .setHeight(meishuAdInfo.getHeight())
                .setDUrl(meishuAdInfo.getdUrl())
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
                .setVideo_cover(meishuAdInfo.getVideo_cover())
                .setClickid(meishuAdInfo.getClickid())
                .build();
        int creativeType = 1;
        DelegateChain nativeADDelegate = null;
        if (meishuAdInfo.getCreative_type() != null) {
            creativeType = meishuAdInfo.getCreative_type();
        }
        adSlot.setAdPatternType(creativeType);
        if (creativeType == 1) {
            adSlot.setImageUrls(meishuAdInfo.getSrcUrls());
            nativeADDelegate = new MeishuAdNativeWrapper(this, adSlot);
        } else if (creativeType == 2) {
            if (meishuAdInfo.getSrcUrls() != null && meishuAdInfo.getSrcUrls().length > 0) {
                adSlot.setVideoUrl(meishuAdInfo.getSrcUrls()[0]);
            }
            nativeADDelegate = new MeishuAdNativeWrapper(this, adSlot);
        } else {
            Log.e(TAG, "", new Exception("不支持的创意类型，类型标识为[" + creativeType + "]"));
        }
        return nativeADDelegate;
    }

    @Override
    protected DelegateChain createDelegate(SdkAdInfo sdkAdInfo) {
        String key = sdkAdInfo.getSdk();
        DelegateChain delegate;
        if ("GDT".equalsIgnoreCase(key)) {
            delegate = new GDTNativeUnifiedAdWrapper(this, sdkAdInfo);
        } else if ("CSJ".equalsIgnoreCase(key)) {
            delegate = new CSJTTAdNativeWrapper(this, sdkAdInfo);
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

    public NativeAdListener getApiAdListener() {
        return apiAdListener;
    }
}
