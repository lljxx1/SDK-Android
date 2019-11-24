package com.meishu.sdkdemo.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.splash.SplashAdLoader;
import com.meishu.sdk.splash.SplashInteractionListener;
import com.meishu.sdkdemo.MainActivity;
import com.meishu.sdkdemo.R;

public class SplashActivity extends AppCompatActivity implements SplashAdListener {
    private static final String TAG = "SplashActivity";

    private SplashAd splashAd;
    private volatile boolean canJump = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewGroup adContainer = findViewById(R.id.splash_container);
        fetchSplashAD(this, adContainer, "1003907", this, 3000);
    }

    private void fetchSplashAD(Activity activity, ViewGroup adContainer, String posId, SplashAdListener adListener, int fetchDelay) {
        SplashAdLoader splashAD = new SplashAdLoader(activity, adContainer, posId, adListener, fetchDelay);
        splashAD.loadAd();
    }

    @Override
    public void onLoaded(SplashAd splashAd) {
        this.splashAd = splashAd;
        splashAd.setInteractionListener(new SplashInteractionListener() {
            @Override
            public void onAdClicked() {
                Log.d(TAG, "onAdClicked: 开屏广告被点击");
            }
        });
    }

    @Override
    public void onAdExposure() {
        Log.d(TAG, "onAdExposure: 开屏广告曝光");
    }

    @Override
    public void onAdClosed() {
        Log.d(TAG, "onAdClosed: 开屏广告被关闭");

        if (canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: 暂停");
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: 开屏界面停止运行");
        super.onStop();
        canJump=true;
    }

    private void next() {
        this.startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if (this.splashAd != null && canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    public void onError() {
        Log.d(TAG, "onError: 没有加载到广告");
    }
}
