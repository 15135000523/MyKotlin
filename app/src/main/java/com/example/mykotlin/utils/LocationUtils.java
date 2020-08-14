package com.example.mykotlin.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class LocationUtils {
    private LocationManager myLocationManager;

    private static LocationUtils locationUtils;

    private String mLongitude = ""; // 经度
    private String mLatitude = ""; // 维度

    private LocationUtils(){}
    public static LocationUtils getInstance() {
        if (locationUtils == null) {
            synchronized (LocationUtils.class) {
                if (locationUtils == null) {
                    locationUtils = new LocationUtils();
                }
            }
        }
        return locationUtils;
    }

    public void create(Activity activity) {
        myLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean gpsIsOpen = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(gpsIsOpen){
            startLocation(activity);
        }
    }
    private void startLocation(Activity activity) {

        // 为获取地理位置信息时设置查询条件 是按GPS定位还是network定位
        String bestProvider = getProvider();

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(activity);
            return;
        }
        Location location = myLocationManager.getLastKnownLocation(bestProvider);
        mLongitude = String.valueOf(location.getLongitude());
        mLatitude = String.valueOf(location.getLatitude());

        Log.e("yan","纬度：" + location.getLatitude() + "\n" +  "经度:  " + location.getLongitude());
    }

    /**
     * 6.0动态申请权限
     */
    private void requestPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 0x66);
        }
    }
    /**
     * 定位查询条件
     * 返回查询条件 ，获取目前设备状态下，最适合的定位方式
     */
    private String getProvider() {
        // 构建位置查询条件
        Criteria criteria = new Criteria();
        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        //Criteria.ACCURACY_FINE,当使用该值时，在建筑物当中，可能定位不了,建议在对定位要求并不是很高的时候用Criteria.ACCURACY_COARSE，避免定位失败
        // 查询精度：高
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 设置是否要求速度
        criteria.setSpeedRequired(false);
        // 是否查询海拨：否
        criteria.setAltitudeRequired(false);
        // 是否查询方位角 : 否
        criteria.setBearingRequired(false);
        // 是否允许付费：是
        criteria.setCostAllowed(false);
        // 电量要求：低
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        // 返回最合适的符合条件的provider，第2个参数为true说明 , 如果只有一个provider是有效的,则返回当前provider
        return myLocationManager.getBestProvider(criteria, true);
    }

}
