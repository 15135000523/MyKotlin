package com.example.mykotlin.http;

public interface ICallback<T> {
    void onSuccess(T t);
    void onError(String e);
}
