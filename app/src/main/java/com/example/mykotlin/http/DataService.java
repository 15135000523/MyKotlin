package com.example.mykotlin.http;

import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.bean.ReBean;
import com.example.mykotlin.ui.youku.main.OrderDetailsBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    @GET("wxarticle/chapters/json")
    Observable<ReBean> getList();

    @GET("article/list/{page}/json")
    Observable<BaseResponse<HomeBean>> getHomeList(@Path("page")String page);

    //查询工单详情
    //gdqx/getgdTdxx?orderId=2d96754468ae40899429194ce0ebfd4a
    @GET("gdqx/getgdTdxx")
    Observable<ResponseBody> getOrderDetails(@Query("orderId") String orderId);

}
