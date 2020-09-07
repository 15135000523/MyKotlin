package com.example.mykotlin.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class WindowUtils {

    private static WindowUtils instance;
    private WindowUtils(){}
    public static WindowUtils getInstance(){
        if(instance ==null){
            synchronized (WindowUtils.class){
                if(instance==null){
                    instance = new WindowUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 背景变暗
     */
    public void backgroundDarken(Activity context,Float alpha){
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha =alpha;
        context.getWindow().setAttributes(lp);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 改变状态栏颜色
     * @param window
     * @param color
     */
    private static void setColor(@NonNull Window window, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(color);
        }
    }

    /**
     * 改变状态栏颜色
     * @param context
     * @param color
     */
    public static void setColor(Context context, @ColorInt int color) {
        if (context instanceof Activity) {
            setColor(((Activity) context).getWindow(), color);
        }

    }
}
