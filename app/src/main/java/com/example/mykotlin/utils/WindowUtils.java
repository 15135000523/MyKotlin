package com.example.mykotlin.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

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
}
