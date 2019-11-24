package com.meishu.sdk.meishu_ad.splash;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * 创建一个LifecycleFragment，用于绑定Activity的生命周期
 * 并通过LifecycleListener接口将监听结果返回给调用者
 */
public class LifecycleFragment extends Fragment {

    private static final String Fragment_TAG = "LifecycleFragment";
    private LifecycleListener lifecycleListener;

    public void setLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    public static LifecycleFragment createLifecycleListenerFragment(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        return getLifecycleListenerFragment(manager);
    }

    //添加空白fragment
    private static LifecycleFragment getLifecycleListenerFragment(FragmentManager manager) {
        LifecycleFragment fragment = (LifecycleFragment) manager.findFragmentByTag(Fragment_TAG);
        if (fragment == null) {
            fragment = new LifecycleFragment();
            manager.beginTransaction().add(fragment, Fragment_TAG).commitAllowingStateLoss();
        }

        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(this.lifecycleListener!=null){
            this.lifecycleListener.onStart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(this.lifecycleListener!=null){
            this.lifecycleListener.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.lifecycleListener!=null){
            this.lifecycleListener.onResume();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if(this.lifecycleListener!=null){
            this.lifecycleListener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(this.lifecycleListener!=null){
            this.lifecycleListener.onDestroy();
        }
    }

}