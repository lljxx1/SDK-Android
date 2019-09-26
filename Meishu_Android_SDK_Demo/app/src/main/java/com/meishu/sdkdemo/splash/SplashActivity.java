package com.meishu.sdkdemo.splash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.meishu.sdk.splash.SplashAd;
import com.meishu.sdk.splash.SplashAdListener;
import com.meishu.sdk.splash.SplashAdLoader;
import com.meishu.sdk.splash.SplashInteractionListener;
import com.meishu.sdkdemo.R;

public class SplashActivity extends AppCompatActivity implements SplashAdListener {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewGroup adContainer = findViewById(R.id.splash_container);
        fetchSplashAD(this, adContainer, "1003907", this, 3000);
    }

    private void fetchSplashAD(Activity activity, ViewGroup adContainer,String posId, SplashAdListener adListener, int fetchDelay) {
        SplashAdLoader splashAD = new SplashAdLoader(activity, adContainer, posId, adListener, fetchDelay);
        splashAD.loadAd();
    }

    @Override
    public void onLoaded(SplashAd bannerAd) {
        bannerAd.setInteractionListener(new SplashInteractionListener() {
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
    }

    @Override
    public void onError() {
        Log.d(TAG, "onError: 没有加载到广告");
    }
}
