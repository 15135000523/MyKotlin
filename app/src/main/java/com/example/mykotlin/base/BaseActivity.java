package com.example.mykotlin.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<VM extends BaseViewModel, V extends ViewDataBinding> extends AppCompatActivity implements IView {

    protected VM mViewModel;
    protected V mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, loadLayout());
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(initViewModel());
        initView();
        initObserver();
        getLifecycle().addObserver(mViewModel);
        tokenError();
    }

    public abstract int loadLayout();

    public abstract Class<VM> initViewModel();

    private void tokenError() {
        mViewModel.mError.observe(this, s -> {
            if (s.equals("102")) {//清除登录数据，重新登录
                finish();
            }
        });
    }
}
