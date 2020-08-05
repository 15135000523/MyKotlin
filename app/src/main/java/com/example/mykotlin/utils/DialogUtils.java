package com.example.mykotlin.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.mykotlin.R;

public class DialogUtils {

    private static DialogUtils instance;

    private DialogUtils(){}
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

    public void createDialog(Context context,View location){
        View view  = LayoutInflater.from(context).inflate(R.layout.item_smart,null,false);
        PopupWindow window = new PopupWindow();
        window.setContentView(view);
        window.setWidth(300);
        window.setHeight(200);
        window.setFocusable(true);
        window.showAsDropDown(location);
    }

}

