package com.example.mykotlin.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogInterceptor implements Interceptor {

    private Builder builder;

    private LogInterceptor(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request;
        if(builder.headers.size()>0){
            Request.Builder a =  chain.request().newBuilder();
            for (String s : builder.headers.keySet()) {
               a.addHeader(s, builder.headers.get(s));
            }
            request = a.build();
        }else{
            request = chain.request();
        }
        printRequest(request);

        Response response = chain.proceed(request);

        return  printResponse(response);
    }

    private void printRequest(Request request) {
        String requestStr = "url:\n" + request.url().toString() + "\n";
        requestStr = requestStr + "method:" + request.method().toString() + "\n";
        if (request.headers().names().size() > 0) {
            for (String name : request.headers().names()) {
                requestStr += "header-)" + name + ":" + request.header(name);
            }
        } else {
            requestStr += "当前接口没有请求头";
        }
        Log.e("yan httpRequset", requestStr);
    }

    private Response printResponse(Response response) {
        String responseStr = "url:\n" + response.request().url() + "\n";
        responseStr = responseStr + "code:" + response.code() + "\n";
        String headers = "";
        if( response.headers().size()>0){
            headers ="header:\n";
            for (String name : response.headers().names()) {
                headers += name + ":" + response.header(name)+"\n";
            }
        }
        responseStr = responseStr + headers + "\n";
        String content = "";
        try {
            content = response.body().string();
            content = toPrettyFormat(content);
            responseStr = responseStr + "body:" + content + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("yan httpResponse", responseStr);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), content))
                .build();
    }

    private  String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

    static class Builder{
        private HashMap<String,String> headers;
        public Builder(){
            headers = new HashMap<>();
        }
        public Builder addHeader(String key,String value){
            headers.put(key,value);
            return this;
        }

        public LogInterceptor build(){
            return new LogInterceptor(this);
        }
    }
}
