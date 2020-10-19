package com.example.mykotlin.rajava;

public class MlxObservable<T> {
    private MlxObservableOnSubscribe sourse;


    public MlxObservable(MlxObservableOnSubscribe sourse) {
        this.sourse = sourse;
    }

    public static  <T> MlxObservable create(MlxObservableOnSubscribe<T> sourse) {
        return new MlxObservable(sourse);
    }

    //这里接收一个下游对象，
    public <T> void setObserver(MlxObserver<T> downStream) {
        sourse.setObserver(downStream);
    }
}
