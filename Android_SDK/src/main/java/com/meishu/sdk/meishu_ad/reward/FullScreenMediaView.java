package com.meishu.sdk.meishu_ad.reward;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.androidquery.AQuery;
import com.meishu.sdk.R;
import com.meishu.sdk.meishu_ad.MediaView;
import com.meishu.sdk.meishu_ad.nativ.NativeAdMediaListener;

import java.util.ArrayList;
import java.util.List;

public class FullScreenMediaView extends FrameLayout implements MediaView {
    private static final String TAG = "FullScreenMediaView";
    private VideoView videoView;
    private AQuery aQuery;
    private CircleProcessBar processBar;
    private MediaPlayer mediaPlayer;

    private OnVideoLoadedListener onVideoLoadedListener;
    private OnVideoKeepTimeFinishListener onVideoKeepTimeFinishListener;

    private long keepTime = -1;

    private NativeAdMediaListener nativeAdMediaListener;

    private volatile boolean keepTimeFinishedPerformed;
    private volatile boolean oneQuarterPerformed;
    private volatile boolean oneHalfPerformed;
    private volatile boolean threeQuarterPerformed;

    /**
     * 播放进度更新消息
     */
    private static final int UPDATE_TIME = 1;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentTime = videoView.getCurrentPosition();
            processBar.refreshProcess(currentTime);


            if (keepTime > 0 && currentTime >= keepTime && !keepTimeFinishedPerformed) {
                keepTimeFinishedPerformed = true;
                if (FullScreenMediaView.this.onVideoKeepTimeFinishListener != null) {
                    FullScreenMediaView.this.onVideoKeepTimeFinishListener.onKeepTimeFinished();
                }
            }

            double totalTime = videoView.getDuration();
            double percent = currentTime / totalTime;
            if (percent >= 0.25 && percent < 0.5) {
                if (!oneQuarterPerformed) {
                    if (nativeAdMediaListener != null) {
                        nativeAdMediaListener.onVideoOneQuarter();
                    }
                    oneQuarterPerformed = true;
                }
            } else if (percent >= 0.5 && percent < 0.75) {
                if (!oneHalfPerformed) {
                    if (nativeAdMediaListener != null) {
                        nativeAdMediaListener.onVideoOneHalf();
                    }
                    oneHalfPerformed = true;
                }
            } else if (percent >= 0.75 && percent < 1) {
                if (!threeQuarterPerformed) {
                    if (nativeAdMediaListener != null) {
                        nativeAdMediaListener.onVideoThreeQuarter();
                    }
                    threeQuarterPerformed = true;
                }
            }

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

    public void setVideoCover(String coverUrl) {
//        videoView.setBackgroundResource();
    }

    private void initEvent() {
        aQuery.id(R.id.center_play_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FullScreenMediaView.this.videoView.isPlaying()) {
                    start();
                }
            }
        });
        aQuery.id(R.id.controlbar_video_play_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FullScreenMediaView.this.videoView.isPlaying()) {
                    start();
                }
            }
        });
        aQuery.id(R.id.controlbar_video_pause_button).clicked(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FullScreenMediaView.this.videoView.isPlaying()) {
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
                FullScreenMediaView.this.mediaPlayer = mp;
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);

                if (started) {
                    onPlay();
                } else {
                    onReset();
                }
                started = false;//设置为false可以避免：正在播放时，重新加载了视频，会出现无法播放的情况
                if (nativeAdMediaListener != null) {
                    nativeAdMediaListener.onVideoLoaded();
                }
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                FullScreenMediaView.this.onCompletion();

            }
        });
    }

    private boolean started;

    public void setOnVideoLoadedListener(OnVideoLoadedListener onVideoLoadedListener) {
        this.onVideoLoadedListener = onVideoLoadedListener;
    }

    @Override
    public void setOnVideoKeepTimeFinishListener(OnVideoKeepTimeFinishListener onVideoKeepTimeFinishListener, long keepTime) {
        this.onVideoKeepTimeFinishListener = onVideoKeepTimeFinishListener;
        if (keepTime > 0) {
            this.keepTime = keepTime;
        }
    }

    private List<OnVideoCompleteListener> onVideoCompleteListeners = new ArrayList<>();

    @Override
    public void addOnVideoCompleteListener(OnVideoCompleteListener onVideoCompleteListener) {
        if (onVideoCompleteListener != null) {
            onVideoCompleteListeners.add(onVideoCompleteListener);
        }
    }

    public void performVideoActions() {
        if (onVideoLoadedListener != null) {
            onVideoLoadedListener.onLoaded(this);
        }
    }

    @Override
    public View getVideoView() {
        return this;
    }

    public void setNativeAdMediaListener(NativeAdMediaListener nativeAdMediaListener) {
        this.nativeAdMediaListener = nativeAdMediaListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: ");
        super.onDetachedFromWindow();
        if (this.uiHandler != null) {
            this.uiHandler.removeCallbacksAndMessages(null);
        }
    }

    private void initUI(Context context) {
        final View mediaPlayerContainer = LayoutInflater.from(context).inflate(R.layout.full_screen_video_player_layout, null);
        this.addView(mediaPlayerContainer);
        this.videoView = mediaPlayerContainer.findViewById(R.id.video_view);

        aQuery = new AQuery(this);
        processBar = (CircleProcessBar) aQuery.id(R.id.process_bar).getView();
    }

    private void onPlay() {
        started = true;
        aQuery.id(R.id.controlbar_video_play_button).visibility(GONE);
        aQuery.id(R.id.center_play_button).visibility(GONE);
        aQuery.id(R.id.controlbar_video_pause_button).visibility(VISIBLE);
        processBar.setmTotalTime(videoView.getDuration());
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
        processBar.refreshProcess(0);
        processBar.setmTotalTime(mediaPlayer.getDuration());
        if (uiHandler.hasMessages(UPDATE_TIME)) {
            uiHandler.removeMessages(UPDATE_TIME);
        }
    }

    private void onCompletion() {
        started = false;
        onPause();
        if (this.keepTime <= 0 && this.onVideoKeepTimeFinishListener != null) {//结束时，若没有回调过onKeepTimeFinished，则回调一次
            this.onVideoKeepTimeFinishListener.onKeepTimeFinished();
        }
        for (OnVideoCompleteListener onVideoCompleteListener : FullScreenMediaView.this.onVideoCompleteListeners) {
            onVideoCompleteListener.onCompleted();
        }
        if (nativeAdMediaListener != null) {
            nativeAdMediaListener.onVideoComplete();
        }
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

    private void init(Context context) {
        initUI(context);
        initEvent();
    }

    public FullScreenMediaView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FullScreenMediaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FullScreenMediaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

}
