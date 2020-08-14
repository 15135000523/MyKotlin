package com.example.mykotlin.ui.smart;

import androidx.lifecycle.MutableLiveData;

import com.example.mykotlin.base.BaseViewModel;
import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.http.BaseResponse;
import com.example.mykotlin.http.DataService;
import com.example.mykotlin.http.IBaseResponseCallBack;

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
