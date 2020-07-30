package com.example.mykotlin.service;

public interface IBaseResponseCallBack<T> {
    void onSuccess(BaseResponse<T> t);
    void onError(String e);
}
