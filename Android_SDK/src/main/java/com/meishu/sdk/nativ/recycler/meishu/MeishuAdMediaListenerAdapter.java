package com.meishu.sdk.nativ.recycler.meishu;

import com.meishu.sdk.meishu_ad.nativ.NativeAdMediaListener;
import com.meishu.sdk.nativ.recycler.AdMediaListener;

public class MeishuAdMediaListenerAdapter implements NativeAdMediaListener {
    private AdMediaListener apiAdMediaListener;

    public MeishuAdMediaListenerAdapter(AdMediaListener apiAdMediaListener) {
        this.apiAdMediaListener = apiAdMediaListener;
    }

    @Override
    public void onVideoLoaded() {
        if(this.apiAdMediaListener!=null){
            this.apiAdMediaListener.onVideoLoaded();
        }
    }

    @Override
    public void onVideoStart() {
        if(this.apiAdMediaListener!=null){
            this.apiAdMediaListener.onVideoStart();
        }
    }

    @Override
    public void onVideoPause() {
        if(this.apiAdMediaListener!=null){
            this.apiAdMediaListener.onVideoPause();
        }
    }

    @Override
    public void onVideoResume() {
        if(this.apiAdMediaListener!=null){
            this.apiAdMediaListener.onVideoResume();
        }
    }

    @Override
    public void onVideoError() {
        if(this.apiAdMediaListener!=null){
            this.apiAdMediaListener.onVideoLoaded();
        }
    }
}
