package com.meishu.sdk.reward.meishu;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.meishu.sdk.activity.RewardVideoPlayerActivity;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.NativeAd;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
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
            NativeAdSlot adSlot = adWrapper.getAdSlot();
            Activity activity = this.adWrapper.getActivity();
            int currentOrientation = activity.getResources().getConfiguration().orientation;
            Intent intent = new Intent();
            intent.setClass(activity, RewardVideoPlayerActivity.class);
            intent.putExtra(RewardVideoPlayerActivity.orientation_key, currentOrientation);

            if (adSlot.getVideo_start() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_start_key, adSlot.getVideo_start());
            }
            if (adSlot.getVideo_one_quarter() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_one_quarter_key, adSlot.getVideo_one_quarter());
            }
            if (adSlot.getVideo_one_half() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_one_half_key, adSlot.getVideo_one_half());
            }
            if (adSlot.getVideo_three_quarter() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_three_quarter_key, adSlot.getVideo_three_quarter());
            }
            if (adSlot.getVideo_complete() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_complete_key, adSlot.getVideo_complete());
            }
            if (adSlot.getVideo_pause() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_pause_key, adSlot.getVideo_pause());
            }
            if (adSlot.getVideo_mute() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_mute_key, adSlot.getVideo_mute());
            }
            if (adSlot.getVideo_unmute() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_unmute_key, adSlot.getVideo_unmute());
            }

            activity.startActivity(intent);
        } else {
            Log.e(TAG, "showAd: ", new Exception("请先加载视频"));
        }
    }
}
