package com.example.mykotlin.viewFactory;

import android.content.Context;
import android.view.View;

import com.example.mykotlin.ViewMap;
import com.example.mykotlin.bean.LoadTypeBean;

public class ViewFactory {


    public static View getInstance(Context context, LoadTypeBean bean) {
        if (bean.getInputType().equals("输入框")) {
            View view = new DialogItemView().createItemView(context, bean);
            return view;
        } else if (bean.getInputType().equals("一级弹窗")) {
            return new DialogItemView().createItemView(context, bean);
        } else if (bean.getInputType().equals("输入数字")) {
            return new DialogItemView().createItemView(context, bean);
        } else if (bean.getInputType().equals("输入弹窗")) {
            return new DialogItemView().createItemView(context, bean);
        } else {
            return null;
        }
    }


}
