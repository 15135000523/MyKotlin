package com.example.mykotlin.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class HttpUtils {

    var  BASE_URL = "http://www.wanandroid.com/";
    lateinit var  mRetrofit: Retrofit
    var mServiceHashMap :HashMap<Any,Retrofit> =HashMap();


   init {
       // init okhttp 3 logger
       var logInterceptor =  HttpLoggingInterceptor();
       // int okhttp
       var client =  OkHttpClient.Builder()
           .retryOnConnectionFailure(true)
           .connectTimeout(15, TimeUnit.SECONDS)
           .readTimeout(45, TimeUnit.SECONDS)
           .writeTimeout(55, TimeUnit.SECONDS)
           .addInterceptor(logInterceptor)
           .build();
       // int retrofit
       mRetrofit =  Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build();

       mServiceHashMap.put("", mRetrofit);
   }

    fun init(){

    }

}