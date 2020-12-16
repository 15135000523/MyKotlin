package com.example.mykotlin.ui.youku.main.fragment.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

public class MyBanner extends RelativeLayout {

    private ViewPager myViewPager;
    private MyViewPagerAdapter myAdapter;
    private IndicatorView myIndicatorView;

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }
}
