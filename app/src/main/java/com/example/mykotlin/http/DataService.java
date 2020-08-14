package com.example.mykotlin.http;

import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.bean.ReBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {

    @GET("wxarticle/chapters/json")
    Observable<ReBean> getList();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<HomeBean>> getHomeList(@Path("page")String page);


}
