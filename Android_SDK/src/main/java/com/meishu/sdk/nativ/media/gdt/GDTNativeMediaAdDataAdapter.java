package com.meishu.sdk.nativ.media.gdt;

import android.view.View;
import android.view.ViewGroup;

import com.meishu.sdk.nativ.media.NativeAdMediaListener;
import com.meishu.sdk.nativ.media.NativeMediaAdData;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;

import java.util.List;

public class GDTNativeMediaAdDataAdapter implements NativeMediaAdData {

    private NativeMediaADData nativeMediaADData;

    public GDTNativeMediaAdDataAdapter(NativeMediaADData nativeMediaADData) {
        this.nativeMediaADData = nativeMediaADData;
    }

    @Override
    public String getTitle() {
        return nativeMediaADData.getTitle();
    }

    @Override
    public String getDesc() {
        return nativeMediaADData.getDesc();
    }

    @Override
    public String getIconUrl() {
        return nativeMediaADData.getIconUrl();
    }

    @Override
    public String getImgUrl() {
        return nativeMediaADData.getImgUrl();
    }

    @Override
    public int getAdPatternType() {
        return nativeMediaADData.getAdPatternType();
    }

    @Override
    public List<String> getImgList() {
        return nativeMediaADData.getImgList();
    }

    @Override
    public int getProgress() {
        return nativeMediaADData.getProgress();
    }

    @Override
    public void bindView(ViewGroup mediaView, boolean var2) {
        MediaView gdtMediaView = new MediaView(mediaView.getContext());
        ViewGroup parent = (ViewGroup) mediaView.getParent();
        parent.removeView(mediaView);
        gdtMediaView.addView(mediaView);
        parent.addView(gdtMediaView);
        nativeMediaADData.bindView(gdtMediaView, var2);
    }

    @Override
    public void play() {
        nativeMediaADData.play();
    }

    @Override
    public void onScroll(int var1, View var2) {
        nativeMediaADData.onScroll(var1, var2);
    }

    @Override
    public void stop() {
        nativeMediaADData.stop();
    }

    @Override
    public boolean isPlaying() {
        return nativeMediaADData.isPlaying();
    }

    @Override
    public int getDuration() {
        return nativeMediaADData.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return nativeMediaADData.getCurrentPosition();
    }

    @Override
    public int getECPM() {
        return nativeMediaADData.getECPM();
    }

    @Override
    public void resume() {
        nativeMediaADData.resume();
    }

    @Override
    public void onExposured(View adInfoContainer) {
        nativeMediaADData.onExposured(adInfoContainer);
    }

    @Override
    public void onClicked(View downloadView) {
        nativeMediaADData.onClicked(downloadView);
    }

    @Override
    public void preLoadVideo() {
        nativeMediaADData.preLoadVideo();
    }

    @Override
    public void setMediaListener(NativeAdMediaListener listener) {
        nativeMediaADData.setMediaListener(new GDTNativeAdMediaListenerImpl(listener));
    }
}
