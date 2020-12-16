package com.example.mykotlin.ui.smart;

import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.http.BaseResponse;
import com.example.mykotlin.http.DataService;
import com.example.mykotlin.http.RetrofitUtil;

import io.reactivex.Observable;

public class SmartRepository {
    public SmartRepository(){ }
    public Observable<BaseResponse<HomeBean>> getHomeList(String page){
        return RetrofitUtil.getInstance().create(DataService.class).getHomeList(page);
    }
}
