package com.meishu.sdk.nativ.media;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface NativeMediaAdData {
    String getTitle();

    String getDesc();

    String getIconUrl();

    String getImgUrl();

    int getAdPatternType();

    List<String> getImgList();

    int getProgress();

    void bindView(ViewGroup mediaContainer, boolean var2);

    void play();

    void onScroll(int var1, View var2);

    void stop();

    boolean isPlaying();

    int getDuration();

    int getCurrentPosition();

    int getECPM();

    void resume();

    void onExposured(View adInfoContainer);

    void onClicked(View downloadView);

    void preLoadVideo();

    void setMediaListener(NativeAdMediaListener listener);
}
