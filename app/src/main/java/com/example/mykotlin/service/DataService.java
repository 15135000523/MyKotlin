package com.example.mykotlin.service;

import com.example.mykotlin.ui.ReBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("todoTaskAppController/myTodoTask.lt")
    Call<BaseResponse<String>> getAlreadyDoneList();

    @GET("sug?code=utf-8&q=裤子")
    Observable<ReBean> getList();
}
