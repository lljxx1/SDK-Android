package com.meishu.sdk.reward.meishu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.meishu.sdk.activity.RewardVideoPlayerActivity;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.NativeAd;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.reward.RewardAdInteractionListener;
import com.meishu.sdk.reward.RewardAdMediaListener;
import com.meishu.sdk.reward.RewardVideoAd;
import com.meishu.sdk.service.ClickHandler;

public class RewardVideoAdAdapter implements RewardVideoAd {
    private static final String TAG = "RewardVideoAdAdapter";
    public static final String broadcast_onclick = "broadcast_onclick";
    public static final String broadcast_onreward = "broadcast_onreward";
    public static final String broadcast_onclosed = "broadcast_onclosed";
    public static final String broadcast_on_video_complete = "broadcast_on_video_complete";

    private NativeAd nativeAd;
    private MeishuRewardVideoAdWrapper adWrapper;
    private RewardAdInteractionListener apiInteractionListener;
    private RewardAdMediaListener apiRewardAdMediaListener;

    private volatile boolean receverRegistered;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (RewardVideoAdAdapter.broadcast_onclick.equalsIgnoreCase(intent.getAction())) {
                if (RewardVideoAdAdapter.this.apiInteractionListener != null) {
                    ClickHandler.handleClick(nativeAd);
                    RewardVideoAdAdapter.this.apiInteractionListener.onAdClicked();
                }
            } else if (RewardVideoAdAdapter.broadcast_onreward.equalsIgnoreCase(intent.getAction())) {
                if (RewardVideoAdAdapter.this.adWrapper.getAdLoader().getApiAdListener() != null) {
                    RewardVideoAdAdapter.this.adWrapper.getAdLoader().getApiAdListener().onReward();
                }
            } else if (RewardVideoAdAdapter.broadcast_onclosed.equalsIgnoreCase(intent.getAction())) {
                onVideoActivityClosed();
            } else if (RewardVideoAdAdapter.broadcast_on_video_complete.equalsIgnoreCase(intent.getAction())) {
                if (RewardVideoAdAdapter.this.apiRewardAdMediaListener != null) {
                    RewardVideoAdAdapter.this.apiRewardAdMediaListener.onVideoCompleted();
                }
            }
        }
    };

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
            if (adSlot.getVideo_endcover() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_endcover, adSlot.getVideo_endcover());
            }
            if (adSlot.getVideo_keep_time() != null) {
                intent.putExtra(RewardVideoPlayerActivity.Video_keep_time, adSlot.getVideo_keep_time());
            }
            if (!TextUtils.isEmpty(adSlot.getTitle())) {
                intent.putExtra(RewardVideoPlayerActivity.Ad_title, adSlot.getTitle());
            }
            if (!TextUtils.isEmpty(adSlot.getDesc())) {
                intent.putExtra(RewardVideoPlayerActivity.Ad_content, adSlot.getDesc());
            }
            receverRegistered = true;

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(RewardVideoAdAdapter.broadcast_onclick);
            intentFilter.addAction(RewardVideoAdAdapter.broadcast_onreward);
            intentFilter.addAction(RewardVideoAdAdapter.broadcast_onclosed);
            intentFilter.addAction(RewardVideoAdAdapter.broadcast_on_video_complete);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(activity);

            localBroadcastManager.registerReceiver(receiver, intentFilter);
            activity.startActivity(intent);
            if(this.adWrapper.getMeishuAdListener()!=null){
                this.adWrapper.getMeishuAdListener().onADExposure();
            }
        } else {
            Log.e(TAG, "showAd: ", new Exception("请先加载视频"));
        }
    }

    @Override
    public void destroy() {
        //do noting
    }

    private void onVideoActivityClosed(){
        if (receverRegistered) {
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.adWrapper.getActivity());
            localBroadcastManager.unregisterReceiver(receiver);
            Log.d(TAG, "onVideoActivityClosed: 激励视频广告回收资源");
        }
        if (RewardVideoAdAdapter.this.adWrapper.getAdLoader().getApiAdListener() != null) {
            RewardVideoAdAdapter.this.adWrapper.getAdLoader().getApiAdListener().onAdClosed();
        }
    }

    @Override
    public void setInteractionListener(RewardAdInteractionListener interactionListener) {
        this.apiInteractionListener = interactionListener;
    }

    @Override
    public void setMediaListener(RewardAdMediaListener rewardAdMediaListener) {
        this.apiRewardAdMediaListener = rewardAdMediaListener;
    }

}
