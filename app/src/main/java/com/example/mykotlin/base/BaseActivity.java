package com.example.mykotlin.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<VM extends BaseViewModel, V extends ViewDataBinding> extends AppCompatActivity implements IView{

    protected VM mViewModel;
    protected V mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this,loadLayout());
        mViewModel = initViewModel();
        initView();
        initObserver();
        getLifecycle().addObserver(mViewModel);
    }
   public abstract int loadLayout();
   public abstract VM initViewModel();
}
