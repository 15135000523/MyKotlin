package com.example.mykotlin.base;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mykotlin.http.RetrofitUtil;

public abstract class BaseViewModel extends ViewModel implements LifecycleObserver {
    protected RetrofitUtil httpUtil = RetrofitUtil.getInstance();
    public MutableLiveData<String> mError = new MutableLiveData<>();
}
