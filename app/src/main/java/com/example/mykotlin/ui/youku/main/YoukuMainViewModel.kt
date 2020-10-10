package com.example.mykotlin.ui.youku.main

import android.util.Log
import com.example.mykotlin.base.BaseViewModel
import com.example.mykotlin.bean.HomeBean
import com.example.mykotlin.http.*
import okhttp3.ResponseBody

class YoukuMainViewModel :BaseViewModel(){

    fun getOrderDetails(orderId:String){
        httpUtil.invoke(httpUtil.create(DataService::class.java).getOrderDetails(orderId),object :ICallback<ResponseBody>{
            override fun onSuccess(t: ResponseBody?) {
                Log.e("data",t?.string())
            }

            override fun onError(e: String?) {
                Log.e("data",e)
            }

        })
    }
}