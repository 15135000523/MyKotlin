package com.example.mykotlin.viewFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mykotlin.R;
import com.example.mykotlin.bean.LoadTypeBean;

public class DialogItemView extends ItemView {
    @Override
    public View createItemView(Context context, LoadTypeBean bean) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_kv, null);
        TextView key = view.findViewById(R.id.key);
        TextView value = view.findViewById(R.id.value);
        key.setText(bean.getKey());
        value.setHint(bean.getValueHint());
        value.setTag("1");
        return view;
    }
}
