package com.example.mykotlin.kotlinText;

import android.util.Log;

public interface DefaultMethod {
    default void foo(){
        bar();
        Log.e("default","this is default method");
        head();
    }

    void bar();
    void head();
}
