package com.meishu.sdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.meishu.sdk.R;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.meishu_ad.nativ.NativeAdWrapper;
import com.meishu.sdk.nativ.recycler.meishu.MeishuAdMediaListenerAdapter;
import com.meishu.sdk.reward.meishu.RewardVideoAdAdapter;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class RewardVideoPlayerActivity extends AppCompatActivity {

    public static final String orientation_key = "orientation_key";

    public static final String Video_start_key = "Video_start_key";
    public static final String Video_one_quarter_key = "Video_one_quarter_key";
    public static final String Video_one_half_key = "Video_one_half_key";
    public static final String Video_three_quarter_key = "Video_three_quarter_key";
    public static final String Video_complete_key = "Video_complete_key";
    public static final String Video_pause_key = "Video_pause_key";
    public static final String Video_mute_key = "Video_mute_key";
    public static final String Video_unmute_key = "Video_unmute_key";
    public static final String Video_replay_key = "Video_replay_key";
    public static final String Video_endcover = "Video_endcover";
    public static final String Video_keep_time = "Video_keep_time";
    public static final String Ad_title = "Ad_title";
    public static final String Ad_content = "Ad_content";

    private static MediaView rewardMediaView;

    private MediaView.OnVideoCompleteListener onCompleteListener;

    private volatile boolean onCompletedInvoked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent params = getIntent();
        int currentOrientation = params.getIntExtra(orientation_key, Configuration.ORIENTATION_PORTRAIT);
        if (currentOrientation == ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_meishu_reward_portrait_video);
        } else if (currentOrientation == ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.activity_meishu_reward_landscape_video);
        }
        final AQuery aQuery = new AQuery(RewardVideoPlayerActivity.this);
        final ImageView videoCover = findViewById(R.id.video_end_cover);
        aQuery.id(R.id.reward_close_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewardVideoPlayerActivity.this.finish();
            }
        });

        final String[] video_start = params.getStringArrayExtra(Video_start_key);
        final String[] video_one_quarter = params.getStringArrayExtra(Video_one_quarter_key);
        final String[] video_one_half = params.getStringArrayExtra(Video_one_half_key);
        final String[] video_three_quarter = params.getStringArrayExtra(Video_three_quarter_key);
        final String[] video_complete = params.getStringArrayExtra(Video_complete_key);
        final String[] video_pause = params.getStringArrayExtra(Video_pause_key);
        final String[] video_mute = params.getStringArrayExtra(Video_mute_key);
        final String[] video_unmute = params.getStringArrayExtra(Video_unmute_key);
        final String[] video_replay = params.getStringArrayExtra(Video_replay_key);
        final String video_endcover = params.getStringExtra(Video_endcover);
        final long video_keep_time = params.getLongExtra(Video_keep_time, -1);
        final String ad_title = params.getStringExtra(Ad_title);
        final String ad_content = params.getStringExtra(Ad_content);

        aQuery.id(R.id.video_playing_reward_ad_title).text(ad_title);
        aQuery.id(R.id.video_playing_reward_ad_content).text(ad_content);
        aQuery.id(R.id.video_playing_ad_info_container).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewardVideoPlayerActivity.this.onClick();
            }
        });
        aQuery.id(R.id.video_playing_download_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewardVideoPlayerActivity.this.onClick();
            }
        });

        if (!TextUtils.isEmpty(ad_title)) {
            aQuery.id(R.id.reward_ad_title).text(ad_title);
        }
        if (!TextUtils.isEmpty(ad_title)) {
            aQuery.id(R.id.reward_ad_content).text(ad_content);
        }

        final RelativeLayout mediaView = findViewById(R.id.media_video);
        if (rewardMediaView != null) {
            rewardMediaView.setOnVideoKeepTimeFinishListener(new MediaView.OnVideoKeepTimeFinishListener() {
                @Override
                public void onKeepTimeFinished() {
                    sendBroadcast(RewardVideoAdAdapter.broadcast_onreward);
                }
            }, video_keep_time * 1000);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rewardMediaView.getVideoView().setLayoutParams(layoutParams);
            onCompleteListener = new MediaView.OnVideoCompleteListener() {
                @Override
                public void onCompleted() {
                    onCompletedInvoked = true;
                    mediaView.setVisibility(View.GONE);
                    videoCover.setVisibility(View.VISIBLE);
                    aQuery.id(R.id.video_playing_ad_info_container).visibility(View.GONE);

                    aQuery.id(R.id.ad_info_container).visibility(View.VISIBLE);
                    aQuery.id(R.id.reward_ad_title).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RewardVideoPlayerActivity.this.onClick();
                        }
                    });
                    aQuery.id(R.id.reward_ad_content).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RewardVideoPlayerActivity.this.onClick();
                        }
                    });
                    aQuery.id(R.id.download_button).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RewardVideoPlayerActivity.this.onClick();
                        }
                    });
                    aQuery.id(R.id.video_end_cover).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RewardVideoPlayerActivity.this.onClick();
                        }
                    });
                    aQuery.id(R.id.reward_close_button).visibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(video_endcover)) {
                        aQuery.id(R.id.video_end_cover).image(video_endcover,
                                false,
                                false,
                                0,
                                AQuery.INVISIBLE,
                                new BitmapAjaxCallback() {
                                    @Override
                                    protected void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                                        iv.setImageBitmap(bm);
                                    }
                                });
                    }
                    sendBroadcast(RewardVideoAdAdapter.broadcast_on_video_complete);
                }
            };
            rewardMediaView.addOnVideoCompleteListener(this.onCompleteListener);
            mediaView.addView(rewardMediaView.getVideoView());

            NativeAdWrapper adWrapper = new NativeAdWrapper() {
                @Override
                public Activity getActivity() {
                    return RewardVideoPlayerActivity.this;
                }

                @Override
                public NativeAdSlot getAdSlot() {
                    NativeAdSlot adSlot = new NativeAdSlot().new Builder()
                            .setVideo_start(video_start)
                            .setVideo_one_quarter(video_one_quarter)
                            .setVideo_one_half(video_one_half)
                            .setVideo_three_quarter(video_three_quarter)
                            .setVideo_complete(video_complete)
                            .setVideo_pause(video_pause)
                            .setVideo_mute(video_mute)
                            .setVideo_unmute(video_unmute)
                            .setVideo_replay(video_replay)
                            .build();
                    return adSlot;
                }
            };
            rewardMediaView.setNativeAdMediaListener(new MeishuAdMediaListenerAdapter(adWrapper, null));
            rewardMediaView.start();
            rewardMediaView = null;//视频只缓存一次
        }
    }

    public static void setRewardMediaView(MediaView rewardMediaView) {
        RewardVideoPlayerActivity.rewardMediaView = rewardMediaView;
    }

    private void onClick() {
        sendBroadcast(RewardVideoAdAdapter.broadcast_onclick);
    }

    private void sendBroadcast(@NonNull String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(RewardVideoPlayerActivity.this);
        localBroadcastManager.sendBroadcast(intent);
    }

    public static MediaView getRewardMediaView() {
        return rewardMediaView;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!this.onCompletedInvoked && this.onCompleteListener != null) {
            this.onCompleteListener.onCompleted();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendBroadcast(RewardVideoAdAdapter.broadcast_onclosed);
    }
}
