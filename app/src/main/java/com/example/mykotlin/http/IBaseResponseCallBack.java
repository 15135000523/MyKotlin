package com.example.mykotlin.http;

public interface IBaseResponseCallBack<T> {
    void onSuccess(BaseResponse<T> t);
    void onError(String e);
}
