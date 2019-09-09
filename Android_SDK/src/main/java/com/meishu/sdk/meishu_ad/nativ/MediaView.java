package com.meishu.sdk.meishu_ad.nativ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.androidquery.AQuery;
import com.meishu.sdk.R;

import java.util.Locale;

public class MediaView extends FrameLayout {

    private VideoView videoView;
    private AQuery aQuery;
    private TextView currentTimeView;
    private TextView endTimeView;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;

    private VideoListener videoListener;
    private NativeAdMediaListener nativeAdMediaListener;

    /**
     * 播放进度更新消息
     */
    private static final int UPDATE_TIME = 1;

    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentTime = videoView.getCurrentPosition();
            updateTimeFormat(currentTimeView, currentTime);
            seekBar.setProgress(currentTime);
            uiHandler.sendEmptyMessageDelayed(UPDATE_TIME, 100);
        }
    };

    public void start() {
        this.videoView.start();
        onPlay();
        if (nativeAdMediaListener != null) {
            nativeAdMediaListener.onVideoStart();
        }
    }

    public void pause() {
        this.videoView.pause();
        onPause();
        if (nativeAdMediaListener != null) {
            nativeAdMediaListener.onVideoPause();
        }
    }

    public void stop() {
        this.mediaPlayer.stop();
        onPause();
    }

    public void setVideoPath(String url) {
        //为videoView设置视频路径
        videoView.setVideoPath(url);
    }

    public void setVideoCover(String coverUrl){
//        videoView.setBackgroundResource();
    }

    private void initEvent() {
        aQuery.id(R.id.center_play_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MediaView.this.videoView.isPlaying()) {
                    start();
                }
            }
        });
        aQuery.id(R.id.controlbar_video_play_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MediaView.this.videoView.isPlaying()) {
                    start();
                }
            }
        });
        aQuery.id(R.id.controlbar_video_pause_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaView.this.videoView.isPlaying()) {
                    pause();
                }
            }
        });
        aQuery.id(R.id.video_volume_mute).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenVolume();
            }
        });
        aQuery.id(R.id.video_volume).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMute();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                MediaView.this.mediaPlayer = mp;
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

                if (started) {
                    onPlay();
                }else{
                    onReset();
                }
                started=false;//设置为false可以避免：正在播放时，重新加载了视频，会出现无法播放的情况
                if (nativeAdMediaListener != null) {
                    nativeAdMediaListener.onVideoLoaded();
                }
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                started = false;
                onCompletiong();
            }
        });
    }

    private boolean started;

    public void setVideoListener(VideoListener videoListener) {
        this.videoListener = videoListener;
    }

    public void performVideoActions() {
        if (videoListener != null) {
            videoListener.onLoaded(this);
        }
    }

    public void setNativeAdMediaListener(NativeAdMediaListener nativeAdMediaListener) {
        this.nativeAdMediaListener = nativeAdMediaListener;
    }

    public static interface VideoListener {
        void onLoaded(MediaView mediaView);
    }

    private void initUI(Context context) {
        final View mediaPlayerContainer = LayoutInflater.from(context).inflate(R.layout.video_player_layout, null);
        this.addView(mediaPlayerContainer);
        this.videoView = mediaPlayerContainer.findViewById(R.id.video_view);

        aQuery = new AQuery(this);
        currentTimeView = aQuery.id(R.id.video_currentTime).getTextView();
        endTimeView = aQuery.id(R.id.video_endTime).getTextView();
        seekBar = aQuery.id(R.id.video_seekBar).getSeekBar();
    }

    private void onPlay() {
        started = true;
        aQuery.id(R.id.controlbar_video_play_button).visibility(GONE);
        aQuery.id(R.id.center_play_button).visibility(GONE);
        aQuery.id(R.id.controlbar_video_pause_button).visibility(VISIBLE);
        seekBar.setMax(videoView.getDuration());
        updateTimeFormat(endTimeView, videoView.getDuration());
        if (!uiHandler.hasMessages(UPDATE_TIME)) {
            uiHandler.sendEmptyMessage(UPDATE_TIME);
        }

    }

    private void onPause() {
        aQuery.id(R.id.controlbar_video_play_button).visibility(VISIBLE);
        aQuery.id(R.id.center_play_button).visibility(VISIBLE);
        aQuery.id(R.id.controlbar_video_pause_button).visibility(GONE);
        if (uiHandler.hasMessages(UPDATE_TIME)) {
            uiHandler.removeMessages(UPDATE_TIME);
        }
    }

    private void onReset() {
        aQuery.id(R.id.controlbar_video_play_button).visibility(VISIBLE);
        aQuery.id(R.id.center_play_button).visibility(VISIBLE);
        aQuery.id(R.id.controlbar_video_pause_button).visibility(GONE);
        updateTimeFormat(currentTimeView, 0);
        updateTimeFormat(endTimeView, videoView.getDuration());
        seekBar.setProgress(0);
        if (uiHandler.hasMessages(UPDATE_TIME)) {
            uiHandler.removeMessages(UPDATE_TIME);
        }
    }

    private void onCompletiong() {
        onPause();
    }

    private void onMute() {
        this.mediaPlayer.setVolume(0, 0);
        aQuery.id(R.id.video_volume_mute).visibility(VISIBLE);
        aQuery.id(R.id.video_volume).visibility(GONE);
    }

    private void onOpenVolume() {
        this.mediaPlayer.setVolume(1, 1);
        aQuery.id(R.id.video_volume_mute).visibility(GONE);
        aQuery.id(R.id.video_volume).visibility(VISIBLE);
    }

    private void updateTimeFormat(TextView tv, int millisecond) {
        int second = millisecond / 1000;
        int mm = second / 60;
        int ss = second % 60;
        tv.setText(String.format(Locale.CHINA, "%02d:%02d", mm, ss));
    }

    private void init(Context context) {
        initUI(context);
        initEvent();
    }

    public MediaView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MediaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MediaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

}
