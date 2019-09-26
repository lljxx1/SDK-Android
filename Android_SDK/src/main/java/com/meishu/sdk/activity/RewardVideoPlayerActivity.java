package com.meishu.sdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.meishu.sdk.R;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.meishu_ad.nativ.NativeAdWrapper;
import com.meishu.sdk.nativ.recycler.meishu.MeishuAdMediaListenerAdapter;

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

    private static MediaView rewardMediaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait_video);
        final Button creativeButton = findViewById(R.id.creative_button);
        final ImageView videoCover = findViewById(R.id.video_cover);

        Intent params = getIntent();
        int currentOrientation = params.getIntExtra(orientation_key, Configuration.ORIENTATION_PORTRAIT);
        if (currentOrientation == ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (currentOrientation == ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        final RelativeLayout mediaView = findViewById(R.id.media_video);
        if (rewardMediaView != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rewardMediaView.getVideoView().setLayoutParams(layoutParams);
            rewardMediaView.addOnVideoCompleteListener(new MediaView.OnVideoCompleteListener() {
                @Override
                public void onCompleted() {
                    mediaView.setVisibility(View.GONE);
                    creativeButton.setVisibility(View.VISIBLE);
                    videoCover.setVisibility(View.VISIBLE);
                }
            });
            mediaView.addView(rewardMediaView.getVideoView());

            final String[] video_start = params.getStringArrayExtra(Video_start_key);
            final String[] video_one_quarter = params.getStringArrayExtra(Video_one_quarter_key);
            final String[] video_one_half = params.getStringArrayExtra(Video_one_half_key);
            final String[] video_three_quarter = params.getStringArrayExtra(Video_three_quarter_key);
            final String[] video_complete = params.getStringArrayExtra(Video_complete_key);
            final String[] video_pause = params.getStringArrayExtra(Video_pause_key);
            final String[] video_mute = params.getStringArrayExtra(Video_mute_key);
            final String[] video_unmute = params.getStringArrayExtra(Video_unmute_key);
            final String[] video_replay = params.getStringArrayExtra(Video_replay_key);

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
            rewardMediaView.setNativeAdMediaListener(new MeishuAdMediaListenerAdapter(adWrapper,null));
            rewardMediaView.start();
            rewardMediaView = null;//视频只缓存一次
        }
    }

    public static void setRewardMediaView(MediaView rewardMediaView) {
        RewardVideoPlayerActivity.rewardMediaView = rewardMediaView;
    }

    public static MediaView getRewardMediaView() {
        return rewardMediaView;
    }
}
