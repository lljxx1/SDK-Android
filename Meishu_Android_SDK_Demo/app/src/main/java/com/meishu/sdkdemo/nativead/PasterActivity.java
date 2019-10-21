package com.meishu.sdkdemo.nativead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.meishu.sdk.nativ.recycler.AdData;
import com.meishu.sdk.nativ.recycler.AdInteractionListener;
import com.meishu.sdk.nativ.recycler.AdMediaListener;
import com.meishu.sdk.nativ.recycler.NativeAdListener;
import com.meishu.sdk.nativ.recycler.NativeAdLoader;
import com.meishu.sdkdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频贴片
 */
public class PasterActivity extends AppCompatActivity implements NativeAdListener {
    private static final String TAG = "PasterActivity";
    private RelativeLayout video_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paster);
        video_container = findViewById(R.id.video_container);
        NativeAdLoader nativeAD = new NativeAdLoader(this, "1003910", this);//文字类广告，文字内容取信息流广告中的title
        nativeAD.loadAd();
    }

    private List<AdData> adDatas;

    @Override
    public void onAdLoaded(List<AdData> adDatas) {
        this.adDatas = adDatas;
        if (adDatas != null && adDatas.size() > 0) {
            this.video_container.setVisibility(View.VISIBLE);
            AdData ad = adDatas.get(0);
            List<View> clickableViews = new ArrayList();
            clickableViews.add(video_container);
            ad.bindAdToView(this, video_container, clickableViews, new AdInteractionListener() {
                @Override
                public void onAdClicked() {
                    Log.d(TAG, "onAdClicked: 广告被点击");
                }
            });
            ad.bindMediaView(video_container, new AdMediaListener() {
                @Override
                public void onVideoLoaded() {
                    Log.d(TAG, "onVideoLoaded: 视频加载成功");
                }

                @Override
                public void onVideoStart() {
                    Log.d(TAG, "onVideoStart: 视频开始");
                }

                @Override
                public void onVideoPause() {
                    Log.d(TAG, "onVideoPause: 视频暂停");
                }

                @Override
                public void onVideoCompleted() {
                    Log.d(TAG, "onVideoCompleted: 视频完成");
                    PasterActivity.this.video_container.setVisibility(View.GONE);
                }

                @Override
                public void onVideoError() {
                    Log.d(TAG, "onVideoError: 视频出错");
                }
            });
        }
    }

    @Override
    public void onAdExposure() {
        Log.d(TAG, "onAdExposure: ");
    }

    @Override
    public void onAdClosed() {
        Log.d(TAG, "onAdClosed: ");
    }

    @Override
    public void onAdError() {
        Log.d(TAG, "onAdError: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.adDatas != null) {
            for (AdData adData : adDatas) {
                adData.destroy();
            }
        }
    }
}