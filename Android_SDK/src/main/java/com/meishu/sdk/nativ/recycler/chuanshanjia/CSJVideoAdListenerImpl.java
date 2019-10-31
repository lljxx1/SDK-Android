package com.meishu.sdk.nativ.recycler.chuanshanjia;

import android.util.Log;

import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.meishu.sdk.nativ.recycler.RecyclerAdMediaListener;

public class CSJVideoAdListenerImpl implements TTFeedAd.VideoAdListener {
    private static final String TAG = "CSJVideoAdListenerImpl";
    private RecyclerAdMediaListener meishuRecyclerAdMediaListener;

    public CSJVideoAdListenerImpl(RecyclerAdMediaListener meishuRecyclerAdMediaListener) {
        this.meishuRecyclerAdMediaListener = meishuRecyclerAdMediaListener;
    }

    @Override
    public void onVideoLoad(TTFeedAd ttFeedAd) {
        if(this.meishuRecyclerAdMediaListener !=null){
            this.meishuRecyclerAdMediaListener.onVideoLoaded();
        }
    }

    @Override
    public void onVideoError(int i, int i1) {
        if(this.meishuRecyclerAdMediaListener !=null){
            this.meishuRecyclerAdMediaListener.onVideoError();
        }
    }

    @Override
    public void onVideoAdStartPlay(TTFeedAd ttFeedAd) {
        if(this.meishuRecyclerAdMediaListener !=null){
            this.meishuRecyclerAdMediaListener.onVideoStart();
        }
    }

    @Override
    public void onVideoAdPaused(TTFeedAd ttFeedAd) {
        if(this.meishuRecyclerAdMediaListener !=null){
            this.meishuRecyclerAdMediaListener.onVideoPause();
        }
    }

    @Override
    public void onVideoAdContinuePlay(TTFeedAd ttFeedAd) {
        Log.d(TAG, "onVideoAdContinuePlay: ");
    }
}
