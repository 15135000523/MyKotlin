package com.example.mykotlin.viewFactory;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mykotlin.bean.LoadTypeBean;

public abstract class ItemView {
    private View view;

    abstract View createItemView(Context context, LoadTypeBean bean);

    public View getView(Context context, LoadTypeBean bean) {
        if (view == null) {
            view = createItemView(context, bean);
        }
        return view;
    }

    public boolean verificationView(Context context) {
        ViewGroup group = (ViewGroup) view;
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i).getTag() != "") {
                if (group.getChildAt(i) instanceof TextView) {
                    TextView var1 = (TextView) group.getChildAt(i);
                    if (TextUtils.isEmpty(var1.getText())) {
                        Toast.makeText(context, var1.getHint(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
