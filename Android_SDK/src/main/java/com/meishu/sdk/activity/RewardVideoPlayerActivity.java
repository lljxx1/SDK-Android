package com.meishu.sdk.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.meishu.sdk.R;
import com.meishu.sdk.meishu_ad.MediaView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class RewardVideoPlayerActivity extends AppCompatActivity {

    public static final String orientation_key = "orientation_key";

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
