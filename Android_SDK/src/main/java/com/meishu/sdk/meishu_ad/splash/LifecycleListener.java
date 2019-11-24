package com.meishu.sdk.meishu_ad.splash;

/**
 * 监听LifecycleFragment生命周期的接口
 */
public interface LifecycleListener {

    void onStart();

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();
}
