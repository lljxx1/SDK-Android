package com.meishu.sdk.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;

import com.meishu.sdk.config.MeishuAdConfig;
import com.meishu.sdk.utils.AddressUtils;
import com.meishu.sdk.utils.GPSUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static Map<String, String> wrapParams(@NonNull Context context, @NonNull String pid) {

        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);


        Map<String, String> params = new HashMap<>();
        params.put("version", "3.0");
        params.put("pid", pid);
        params.put("is_mobile", "1");
        params.put("app_package", context.getPackageName());
        params.put("app_id", MeishuAdConfig.getInstance().getAppId());
        params.put("app_name", getAppName(context));
        params.put("app_ver", getVersionName(context));
        GPSUtils gpsUtils = new GPSUtils(context);
        Location location = gpsUtils.getLocation();
        params.put("device_geo_lat", location == null ? "" : Double.valueOf(location.getLatitude()).toString());
        params.put("device_geo_lon", location == null ? "" : Double.valueOf(location.getLongitude()).toString());
        String imei = "";
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei = tm.getImei();
            } else {
                imei = tm.getDeviceId();
            }
        }
        params.put("device_imei", imei);
        params.put("device_adid", getAndroidId(context));
//        params.put("device_openudid", "");
//        params.put("device_idfv", "");
        params.put("device_ppi", "" + getPPI(context));
        params.put("device_mac", AddressUtils.getMac(context));
        params.put("device_type_os", Build.VERSION.RELEASE);
        params.put("device_type", isPad(context) ? "1" : "0");
        params.put("device_brand", Build.BRAND);
        params.put("device_model", Build.MODEL);
        params.put("device_width", context.getResources().getDisplayMetrics().widthPixels + "");
        params.put("device_height", context.getResources().getDisplayMetrics().heightPixels + "");
        params.put("device_imsi", getIMSI(context));
        params.put("device_network", getNetworkType(context));
        params.put("device_os", "Android");
//        params.put("device_ip", "");
        params.put("device_ua", WebSettings.getDefaultUserAgent(context));
        params.put("device_orientation", isScreenOriatationPortrait(context) ? "0" : "1");
        return params;
    }

    private static int getPPI(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    private static String getAndroidId(Context context) {
        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return ANDROID_ID;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本名称
     *
     * @param context 上下文
     * @return 版本名称
     */
    private static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    private static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取手机IMSI
     */
    private static String getIMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMSI号
            String imsi;
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                imsi = "";
            } else {
                imsi = telephonyManager.getSubscriberId();
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getNetworkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo==null?"":networkInfo.getTypeName();
    }

    /**
     * 返回当前屏幕是否为竖屏。
     *
     * @param context
     * @return 当且仅当当前屏幕为竖屏时返回true, 否则返回false。
     */
    private static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
