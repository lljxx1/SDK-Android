package com.meishu.sdk.reward.meishu;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.meishu.sdk.activity.RewardVideoPlayerActivity;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.NativeAd;
import com.meishu.sdk.reward.RewardVideoAd;

public class RewardVideoAdAdapter implements RewardVideoAd {
    private static final String TAG = "RewardVideoAdAdapter";
    private NativeAd nativeAd;
    private MeishuRewardVideoAdWrapper adWrapper;

    public RewardVideoAdAdapter(@NonNull MeishuRewardVideoAdWrapper adWrapper, @NonNull NativeAd nativeAd) {
        this.adWrapper = adWrapper;
        this.nativeAd = nativeAd;
    }

    @Override
    public void showAd() {
        MediaView rewardMediaView = RewardVideoPlayerActivity.getRewardMediaView();
        if (rewardMediaView != null) {
            Activity activity = this.adWrapper.getActivity();
            int currentOrientation = activity.getResources().getConfiguration().orientation;
            Intent intent = new Intent();
            intent.setClass(activity, RewardVideoPlayerActivity.class);
            intent.putExtra(RewardVideoPlayerActivity.orientation_key, currentOrientation);
            activity.startActivity(intent);
        } else {
            Log.e(TAG, "showAd: ", new Exception("请先加载视频"));
        }
    }
}
