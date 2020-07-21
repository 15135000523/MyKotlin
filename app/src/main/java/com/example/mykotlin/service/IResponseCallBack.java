package com.example.mykotlin.service;

public interface IResponseCallBack<T> {
    void onSuccess(T t);
    void onError(Throwable e);
}
