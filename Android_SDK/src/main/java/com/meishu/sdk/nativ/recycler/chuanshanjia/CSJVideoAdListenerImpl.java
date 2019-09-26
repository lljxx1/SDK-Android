package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.util.Log;

import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.meishu.sdk.nativ.recycler.AdMediaListener;

public class CSJVideoAdListenerImpl implements TTFeedAd.VideoAdListener {
    private static final String TAG = "CSJVideoAdListenerImpl";
    private AdMediaListener meishuAdMediaListener;

    public CSJVideoAdListenerImpl(AdMediaListener meishuAdMediaListener) {
        this.meishuAdMediaListener = meishuAdMediaListener;
    }

    @Override
    public void onVideoLoad(TTFeedAd ttFeedAd) {
        if(this.meishuAdMediaListener!=null){
            this.meishuAdMediaListener.onVideoLoaded();
        }
    }

    @Override
    public void onVideoError(int i, int i1) {
        if(this.meishuAdMediaListener!=null){
            this.meishuAdMediaListener.onVideoError();
        }
    }

    @Override
    public void onVideoAdStartPlay(TTFeedAd ttFeedAd) {
        if(this.meishuAdMediaListener!=null){
            this.meishuAdMediaListener.onVideoStart();
        }
    }

    @Override
    public void onVideoAdPaused(TTFeedAd ttFeedAd) {
        if(this.meishuAdMediaListener!=null){
            this.meishuAdMediaListener.onVideoPause();
        }
    }

    @Override
    public void onVideoAdContinuePlay(TTFeedAd ttFeedAd) {
        Log.d(TAG, "onVideoAdContinuePlay: ");
    }
}
