package com.example.navigationlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.navisdk.adapter.struct.BNaviInfo;

// 点击诱导面板 用于展示导航信息回调的pop
public class GuidePop extends PopupWindow {

    View mPopView;

    public GuidePop(Context context) {
        super(context);
        init(context);
        setpopwindow(context);
    }

    public void setdata(final BNaviInfo info) {
        mPopView.post(new Runnable() {
            @Override
            public void run() {
                nextloadNameTv.setText("下个路口：" + info.getRoadName());
                nextloadDistanceTv.setText(info.getDistance() + "米 " + info.getTime() + "s后 ");
                turnIcon.setImageBitmap(info.getTurnIcon());
            }
        });
    }

    public void setdata(final String name) {
        mPopView.post(new Runnable() {
            @Override
            public void run() {
                roadname.setText("当前：" + name);
            }
        });
    }

    public void setdata(final int remainDistance, final int remainTime) {
        mPopView.post(new Runnable() {
            @Override
            public void run() {
                remainDistanceTv.setText("剩余 距离：" + remainDistance + "米 时间：" + remainTime + "s");
            }
        });
    }


    private void setpopwindow(final Context context) {
        this.setContentView(mPopView); // 设置View
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT); // 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT); // 设置弹出窗口的高
        this.setFocusable(true); // 设置弹出窗口可
        this.setBackgroundDrawable(new ColorDrawable(0x00000000)); // 设置背景透明
        this.setOutsideTouchable(true);
        this.setOnDismissListener(new OnDismissListener() { // 退出popupwidon时显示父控件原来的颜色
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 1.0f;
                ((Activity) context).getWindow().setAttributes(lp);
            }
        });
    }

    TextView nextloadDistanceTv;
    TextView nextloadTimeTv;
    TextView nextloadNameTv;
    TextView remainDistanceTv;
    TextView remainTimeTv;
    TextView roadname;

    ImageView turnIcon;

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.guidepopview, null);
        nextloadDistanceTv = mPopView.findViewById(R.id.nextload_distance);
        nextloadTimeTv = mPopView.findViewById(R.id.nextload_time);
        nextloadNameTv = mPopView.findViewById(R.id.nextload_name);
        remainDistanceTv = mPopView.findViewById(R.id.remainDistance);
        remainTimeTv = mPopView.findViewById(R.id.remainTime);
        turnIcon = mPopView.findViewById(R.id.turnIcon);

        roadname = mPopView.findViewById(R.id.roadname);
    }

}