package com.meishu.sdk.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;


import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 * 获取用户的地理位置
 */
public class GPSUtils {

    private Context mContext;
    private LocationManager locationManager;

    public GPSUtils(Context context) {
        this.mContext = context;
    }

    /**
     * 获取经纬度
     *
     * @return
     */
//    public void getLngAndLat(OnLocationResultListener onLocationResultListener) {
//
//        mOnLocationListener = onLocationResultListener;
//
//        String locationProvider = null;
//        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        //获取所有可用的位置提供器
//        List<String> providers = locationManager.getProviders(true);
//
//        if (providers.contains(LocationManager.GPS_PROVIDER)) {
//            //如果是GPS
//            locationProvider = LocationManager.GPS_PROVIDER;
//        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//            //如果是Network
//            locationProvider = LocationManager.NETWORK_PROVIDER;
//        } else {
//            Intent i = new Intent();
//            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            mContext.startActivity(i);
//            return;
//        }
//
//        //获取Location
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(locationProvider);
//        if (location != null) {
//            //不为空,显示地理位置经纬度
//            if (mOnLocationListener != null) {
//                mOnLocationListener.onLocationResult(location);
//            }
//
//        }
//        //监视地理位置变化
//        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
//    }


    /**
     * 获取经纬度
     *
     */
    public Location getLocation() {

        String locationProvider = null;
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            //没有权限
//            Intent i = new Intent();
//            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            mContext.startActivity(i);
            return null;
        }

        //获取Location
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return locationManager.getLastKnownLocation(locationProvider);
    }

    public LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (mOnLocationListener != null) {
                mOnLocationListener.OnLocationChange(location);
            }
        }
    };

    public void removeListener() {
        locationManager.removeUpdates(locationListener);
    }

    private OnLocationResultListener mOnLocationListener;

    public interface OnLocationResultListener {
        void onLocationResult(Location location);

        void OnLocationChange(Location location);

        double getLatitude();
        double getLongitude();
    }
}
