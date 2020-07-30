package com.example.mykotlin.service;

public interface ICallback<T> {
    void onSuccess(T t);
    void onError(String e);
}
