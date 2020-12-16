package com.example.mykotlin.ui.youku.main.fragment.banner;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public abstract class MyViewPagerAdapter extends PagerAdapter {
    @Override
    public abstract int getCount();

    @Override
    public abstract boolean isViewFromObject(@NonNull View view, @NonNull Object object);
}
