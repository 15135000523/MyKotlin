package com.example.mykotlin.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseFragment<VM extends BaseViewModel, V extends ViewDataBinding> extends Fragment implements IView {

    protected VM mViewModel;
    protected V mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, loadLayout(), container, false);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        initView();
        initObserver();
        getLifecycle().addObserver(mViewModel);
        tokenError();
        return mDataBinding.getRoot();
    }

    public abstract int loadLayout();

    public abstract Class<VM> initViewModel();

    @SuppressLint("FragmentLiveDataObserve")
    private void tokenError() {
        mViewModel.mError.observe(this, s -> {
            if (s.equals("102")) {//清除登录数据，重新登录
                getActivity().finish();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
