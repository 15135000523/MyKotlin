package com.example.mykotlin.ui.smart;

import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.bean.Weather;
import com.example.mykotlin.http.BaseResponse;
import com.example.mykotlin.http.DataService;
import com.example.mykotlin.http.RetrofitUtil;

import io.reactivex.Observable;

public class SmartRepository {
    public SmartRepository() {
    }

    public Observable<BaseResponse<HomeBean>> getHomeList(String page) {
        return RetrofitUtil.getInstance().create(DataService.class).getHomeList(page);
    }

    public Observable<Weather> getWeather(String location) {
        return RetrofitUtil.getInstance().create(DataService.class).getWeather("db86a5196f304e52a4369818c5182e60", location);
    }
}
