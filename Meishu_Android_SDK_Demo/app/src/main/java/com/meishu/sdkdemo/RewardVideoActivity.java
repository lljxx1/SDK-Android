package com.meishu.sdkdemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.meishu.sdk.reward.RewardVideoAd;
import com.meishu.sdk.reward.RewardVideoAdListener;
import com.meishu.sdk.reward.RewardVideoLoader;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class RewardVideoActivity extends AppCompatActivity implements View.OnClickListener, RewardVideoAdListener {
    private static final String TAG = "RewardVideoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_video);
        findViewById(R.id.change_orientation).setOnClickListener(this);
        findViewById(R.id.load_video).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_orientation:
                int currentOrientation = getResources().getConfiguration().orientation;
                if (currentOrientation == ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if (currentOrientation == ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
            case R.id.load_video:
                RewardVideoLoader loader = new RewardVideoLoader(this, "1003910", this);
                loader.loadAd();
                break;
        }
    }

    @Override
    public void onAdLoaded(RewardVideoAd ad) {
        ad.showAd();
    }

    @Override
    public void onVideoCached() {
        Log.d(TAG, "onVideoCached: ");
    }

    @Override
    public void onAdError() {
        Log.d(TAG, "onAdError: ");
    }
}
