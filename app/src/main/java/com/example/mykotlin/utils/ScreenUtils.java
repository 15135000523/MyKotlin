package com.example.mykotlin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenUtils {

    private static ScreenUtils instance;
    private ScreenUtils(){}

    public static ScreenUtils getInstance(){
        if(instance==null){
            synchronized (DialogUtils.class){
                if (instance==null){
                    instance = new ScreenUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    private static DisplayMetrics getDisplayMetrics(Context context){
        AppCompatActivity activity = (AppCompatActivity) context;
        DisplayMetrics displayMetrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    public static int getScreenHeight(Context context){
        return getDisplayMetrics(context).heightPixels;
    }
    public static int getScreenWidth(Context context){
        return getDisplayMetrics(context).widthPixels;
    }
}
