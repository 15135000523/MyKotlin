package com.example.mykotlin.base;

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
        mDataBinding = (V) loadLayout(inflater,container);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        initView();
        initObserver();
        getLifecycle().addObserver(mViewModel);
        return mDataBinding.getRoot();
    }

    public abstract ViewDataBinding loadLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public abstract Class<VM> initViewModel();

    @Override
    public void onStop() {
        super.onStop();
    }
}
