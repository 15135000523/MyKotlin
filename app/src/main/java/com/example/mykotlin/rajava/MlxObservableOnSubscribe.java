package com.example.mykotlin.rajava;

/**
 * 被观察者
 */
public interface MlxObservableOnSubscribe<T> {
    //设置下游
    void setObserver(MlxObserver<T> observer);
}
