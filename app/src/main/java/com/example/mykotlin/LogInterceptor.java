package com.example.mykotlin;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e("------","Interceptor:" +chain.request().toString());
        Request request = chain.request();
        request= request.newBuilder().addHeader("token","").build();
        return  chain.proceed(request);
    }
}
