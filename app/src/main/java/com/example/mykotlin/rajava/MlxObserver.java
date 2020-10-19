package com.example.mykotlin.rajava;

/**
 * 观察者
 */
public interface MlxObserver<T> {

    void onSubscribe();
    void onNext(T t);
    void onError(Throwable e);
    void onComplete();
}
