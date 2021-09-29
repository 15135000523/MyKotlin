package com.example.mykotlin.ui.smart;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mykotlin.base.BaseViewModel;
import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.bean.Weather;
import com.example.mykotlin.http.BaseResponse;
import com.example.mykotlin.http.IBaseResponseCallBack;
import com.example.mykotlin.http.ICallback;


public class SmartViewModel extends BaseViewModel {
    private SmartRepository repository = new SmartRepository();
    MutableLiveData<HomeBean> mData = new MutableLiveData<>();
    MutableLiveData<Weather> mWeather = new MutableLiveData<>();

    public void getService(String page) {
        httpUtil.invoke(repository.getHomeList(page), new IBaseResponseCallBack<HomeBean>() {
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

    public void getWeather(String location) {
        httpUtil.invoke(repository.getWeather(location), new ICallback<Weather>() {

            @Override
            public void onSuccess(Weather weather) {
                mWeather.setValue(weather);
            }

            @Override
            public void onError(String e) {
                Log.e("Weather", e);
            }
        });
    }
}
