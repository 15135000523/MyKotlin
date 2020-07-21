package com.example.mykotlin.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Location implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create(){
        Log.e("Observer","create");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause(){
        Log.e("Observer","pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        Log.e("Observer","stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy(){
        Log.e("Observer","destroy");
    }
}
