package com.example.mykotlin.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.mykotlin.R;

public class DialogUtils {

    private int width;
    private int height;
    private int showView;
    private boolean isFocusable;

    private static DialogUtils instance;

    private DialogUtils(){}
    private DialogUtils(int width, int height, int showView, boolean isFocusable) {
        this.width = width;
        this.height = height;
        this.showView = showView;
        this.isFocusable = isFocusable;
    }
    public static DialogUtils getInstance(){
        if(instance==null){
            synchronized (DialogUtils.class){
                if (instance==null){
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }



    /**
     * 创建弹窗
     * @param context
     * @param location 弹窗显示的位置
     */
    public void createDialog(Context context,View location){
        View view  = LayoutInflater.from(context).inflate(showView,null,false);
        PopupWindow window = new PopupWindow();
        window.setContentView(view);
        window.setWidth(width);
        window.setHeight(height);
        window.setFocusable(isFocusable);
        window.showAsDropDown(location);
    }

    class Buidler{
        private int width;
        private int height;
        private int showView;
        private boolean isFocusable;

        Buidler width(int width){
            this.width = width;
            return this;
        }
        Buidler height(int height){
            this.height = height;
            return this;
        }
        Buidler showView(int showView){
            this.showView = showView;
            return this;
        }
        Buidler isFocusable(boolean isFocusable){
            this.isFocusable = isFocusable;
            return this;
        }

        public DialogUtils build(){
            return new DialogUtils(width,height,showView,isFocusable);
        }
    }

}

