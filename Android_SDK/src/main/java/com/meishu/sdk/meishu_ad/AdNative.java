package com.meishu.sdk.meishu_ad;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.meishu.sdk.R;
import com.meishu.sdk.meishu_ad.banner.BannerAdSlot;
import com.meishu.sdk.meishu_ad.banner.BannerAdImpl;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdSlot;
import com.meishu.sdk.meishu_ad.interstitial.InterstitialAdImpl;
import com.meishu.sdk.meishu_ad.interstitial.Popup;
import com.meishu.sdk.meishu_ad.nativ.MediaView;
import com.meishu.sdk.meishu_ad.nativ.NativeAdData;
import com.meishu.sdk.meishu_ad.nativ.NativeAdDataImpl;
import com.meishu.sdk.meishu_ad.nativ.NativeAdSlot;
import com.meishu.sdk.meishu_ad.splash.SplashAdSlot;
import com.meishu.sdk.meishu_ad.splash.SkipView;
import com.meishu.sdk.meishu_ad.splash.SplashAdImpl;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;
import razerdp.util.SimpleAnimationUtils;

public class AdNative {
    private static final String TAG = "AdNative";
    private Activity activity;

    public AdNative(@NonNull Activity activity) {
        this.activity = activity;
    }

    public void loadBannerAd(final BannerAdSlot adSlot, final com.meishu.sdk.meishu_ad.banner.AdListener adListener) {

        ImageView adView = new ImageView(this.activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        adView.setLayoutParams(layoutParams);
        LayoutInflater inflater = LayoutInflater.from(this.activity);
        final View bannerRoot = inflater.inflate(R.layout.banner_ad_layout, null);
        final AQuery aQuery = new AQuery(bannerRoot);
        final BannerAdImpl nativeBannerAd = new BannerAdImpl(adSlot);
        if (adSlot.getImageUrls() != null && adSlot.getImageUrls().length > 0) {
            aQuery.id(R.id.banner_image).image(adSlot.getImageUrls()[0],
                    false,
                    false,
                    0,
                    AQuery.INVISIBLE,
                    new BitmapAjaxCallback() {
                        @Override
                        protected void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                            iv.setImageBitmap(bm);
                            if (adListener != null) {
                                nativeBannerAd.setAdView(bannerRoot);
                                adListener.onLoaded(nativeBannerAd);
                                adListener.onADExposure();
                            }
                        }
                    });
        }
        aQuery.id(R.id.banner_close_button).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bannerRoot.getParent() != null) {
                    ((ViewGroup) bannerRoot.getParent()).removeView(bannerRoot);
                }
                if (adListener != null) {
                    adListener.onADClosed();
                }
            }
        });

        aQuery.id(R.id.banner_image).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nativeBannerAd.getInteractionListener() != null) {
                    nativeBannerAd.getInteractionListener().onAdClicked();
                }
            }
        });

    }

    public void loadSplashAd(final SplashAdSlot adSlot, final com.meishu.sdk.meishu_ad.splash.AdListener adListener) {

        ImageView adView = new ImageView(this.activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        adView.setLayoutParams(layoutParams);
        LayoutInflater inflater = LayoutInflater.from(this.activity);
        final View adRoot = inflater.inflate(R.layout.splash_ad_layout, null);
        final AQuery aQuery = new AQuery(adRoot);
        final SplashAdImpl nativeAd = new SplashAdImpl(adSlot);
        if (adSlot.getImageUrls() != null && adSlot.getImageUrls().length > 0) {
            aQuery.id(R.id.splash_image).image(adSlot.getImageUrls()[0],
                    false,
                    false,
                    0,
                    AQuery.INVISIBLE,
                    new BitmapAjaxCallback() {
                        @Override
                        protected void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                            iv.setImageBitmap(bm);
                            nativeAd.setAdView(adRoot);
                            if (adSlot.getAdContainer() != null) {
                                adSlot.getAdContainer().addView(nativeAd.getAdView());
                            }
                            if (adListener != null) {
                                adListener.onLoaded(nativeAd);
                                adListener.onADExposure();
                            }
                            final SkipView skipView = nativeAd.getAdView().findViewById(R.id.skipView);
                            skipView.setOnSkipListener(new SkipView.OnSkipListener() {
                                @Override
                                public void onSkip() {
                                    Log.d(TAG, "onSkip: ");
                                    if (nativeAd.getInteractionListener() != null) {
                                        nativeAd.getInteractionListener().onSkip();
                                        if (adRoot.getParent() != null) {
                                            ((ViewGroup) adRoot.getParent()).removeView(adRoot);
                                        }
                                        if (adListener != null) {
                                            adListener.onADClosed();
                                        }
                                    }
                                }
                            });
                            skipView.start();
                            aQuery.id(R.id.splash_image).clicked(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (nativeAd.getInteractionListener() != null) {
                                        nativeAd.getInteractionListener().onAdClicked();
                                    }
                                }
                            });
                        }
                    });

        }

    }

    public void loadInstitialAd(final InterstitialAdSlot adSlot, final com.meishu.sdk.meishu_ad.interstitial.AdListener adListener) {

        final Popup popup = createPopupWindow(this.activity, adSlot.getWidth(), adSlot.getHeight());
        popup.setViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                adListener.onADClosed();
            }
        }, popup.findViewById(R.id.popupwindow_cancel));
        popup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                adListener.onADClosed();
            }
        });
        final View interstitialRoot = popup.getContentView();
        final AQuery aQuery = new AQuery(interstitialRoot);
        final InterstitialAdImpl nativeAd = new InterstitialAdImpl(adSlot);
        if (adSlot.getImageUrls() != null && adSlot.getImageUrls().length > 0) {
            aQuery.id(R.id.interstitial_image).image(adSlot.getImageUrls()[0],
                    false,
                    false,
                    0,
                    AQuery.INVISIBLE,
                    new BitmapAjaxCallback() {
                        @Override
                        protected void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                            iv.setImageBitmap(bm);
                            if (adListener != null) {
                                nativeAd.setAdView(interstitialRoot);
                                popup.showPopupWindow();
                                adListener.onLoaded(nativeAd);
                                adListener.onADExposure();
                            }
                        }
                    });

        }
        aQuery.id(R.id.interstitial_image).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nativeAd.getInteractionListener() != null) {
                    nativeAd.getInteractionListener().onAdClicked();
                }
            }
        });

    }

    public void loadNativeAd(final NativeAdSlot adSlot, final com.meishu.sdk.meishu_ad.nativ.AdListener adListener) {

        final View rootView = activity.findViewById(android.R.id.content);
        final AQuery aQuery = new AQuery(rootView);
        int fetchCount = 1;
        if (adSlot.getFetchCount() != 0) {
            fetchCount = adSlot.getFetchCount();
        }

        final List<NativeAdData> adDatas = new ArrayList<>();
        for (int i = 0; i < fetchCount; i++) {
            MediaView mediaView = new MediaView(this.activity);
            mediaView.setVideoListener(new MediaView.VideoListener() {
                @Override
                public void onLoaded(MediaView mediaView) {
                    adDatas.add(new NativeAdDataImpl(adSlot).new Builder()
                            .setTitle(adSlot.getTitle())
                            .setDesc(adSlot.getDesc())
                            .setAdPatternType(adSlot.getAdPatternType())
                            .setIconUrl(adSlot.getIconUrl())
                            .setImgUrls(adSlot.getImageUrls())
                            .setMediaView(mediaView)
                            .build()
                    );
                    if (adListener != null) {
                        adListener.onAdLoaded(adDatas);
                    }
                    if (adSlot.getAdPatternType() == 2) {//创意类型为视频时，播放视频
                        mediaView.start();
                    }
                    if (adListener != null) {
                        adListener.onADExposure();
                    }
                }
            });

            if (adSlot.getAdPatternType() == 2) {//创意类型为视频时，加载视频
//                mediaView.setVideoPath("android.resource://" + activity.getPackageName() + "/" + R.raw.video_sample_2);
                mediaView.setVideoPath(adSlot.getVideoUrl());
            }
            mediaView.performVideoActions();
        }

    }

    private Popup createPopupWindow(Activity activity, int width, int height) {

        final Popup pw = new Popup(activity, width, height);
        pw.setPopupGravity(Gravity.CENTER)
                .setShowAnimation(SimpleAnimationUtils.getTranslateVerticalAnimation(1f, 0, 200))
                .setDismissAnimation(SimpleAnimationUtils.getTranslateVerticalAnimation(0, 1f, 200));
        pw.setOutSideDismiss(false);
        return pw;
    }
}