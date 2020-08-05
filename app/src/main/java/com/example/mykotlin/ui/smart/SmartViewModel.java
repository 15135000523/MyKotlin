package com.example.mykotlin.ui.smart;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mykotlin.base.BaseViewModel;
import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.service.BaseResponse;
import com.example.mykotlin.service.DataService;
import com.example.mykotlin.service.IBaseResponseCallBack;

public class SmartViewModel extends BaseViewModel {
    MutableLiveData<HomeBean> mData = new MutableLiveData<>();

    public void getService(String page) {
        httpUtil.invoke(httpUtil.create(DataService.class).getHomeList(page), new IBaseResponseCallBack<HomeBean>() {
            @Override
            public void onSuccess(BaseResponse<HomeBean> t) {
                mData.postValue(t.getData());
            }

            @Override
            public void onError(String e) {
                mError.postValue(e);
            }
        });
    }
}
