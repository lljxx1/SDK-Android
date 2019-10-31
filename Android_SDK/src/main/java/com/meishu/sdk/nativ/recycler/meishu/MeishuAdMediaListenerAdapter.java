package com.meishu.sdk.nativ.recycler.meishu;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.meishu.sdk.meishu_ad.nativ.NativeAdMediaListener;
import com.meishu.sdk.meishu_ad.nativ.NativeAdWrapper;
import com.meishu.sdk.nativ.recycler.RecyclerAdMediaListener;
import com.meishu.sdk.utils.DefaultHttpGetWithNoHandlerCallback;
import com.meishu.sdk.utils.HttpUtil;

public class MeishuAdMediaListenerAdapter implements NativeAdMediaListener {
    private RecyclerAdMediaListener apiRecyclerAdMediaListener;
    private NativeAdWrapper adWrapper;

    public MeishuAdMediaListenerAdapter(@NonNull NativeAdWrapper adWrapper, RecyclerAdMediaListener apiRecyclerAdMediaListener) {
        this.adWrapper = adWrapper;
        this.apiRecyclerAdMediaListener = apiRecyclerAdMediaListener;
    }

    @Override
    public void onVideoLoaded() {
        if (this.apiRecyclerAdMediaListener != null) {
            this.apiRecyclerAdMediaListener.onVideoLoaded();
        }
    }

    @Override
    public void onVideoStart() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_start();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        if (this.apiRecyclerAdMediaListener != null) {
            this.apiRecyclerAdMediaListener.onVideoStart();
        }
    }

    @Override
    public void onVideoOneQuarter() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_one_quarter();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoOneHalf() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_one_half();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoThreeQuarter() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_three_quarter();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoComplete() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_complete();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        if (this.apiRecyclerAdMediaListener != null) {
            this.apiRecyclerAdMediaListener.onVideoCompleted();
        }
    }

    @Override
    public void onVideoPause() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_pause();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
        if (this.apiRecyclerAdMediaListener != null) {
            this.apiRecyclerAdMediaListener.onVideoPause();
        }
    }

    @Override
    public void onVideoMute() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_mute();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoUnmute() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_unmute();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoReplay() {
        String[] urls = this.adWrapper.getAdSlot().getVideo_replay();
        if (urls != null) {
            for (String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    HttpUtil.asyncGetWithWebViewUA(this.adWrapper.getActivity(), url, new DefaultHttpGetWithNoHandlerCallback());
                }
            }
        }
    }

    @Override
    public void onVideoError() {
        if (this.apiRecyclerAdMediaListener != null) {
            this.apiRecyclerAdMediaListener.onVideoLoaded();
        }
    }
}
