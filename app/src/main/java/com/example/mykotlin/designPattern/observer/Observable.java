package com.example.mykotlin.designPattern.observer;

import java.util.Vector;

/**
 * 被观察者
 */
public class Observable {
    private Vector<Observer> observerList = new Vector<>();

    public void addObserver(Observer item){
        observerList.add(item);
    }

    public void clearObserver(){
        observerList.clear();
    }

    public void deleteObserverForClass(Observer deletaItem){
        observerList.remove(deletaItem);
    }

    public void notifationChange(){
        for (Observer observer : observerList) {
            observer.notifaDatachange();
        }
    }
}
