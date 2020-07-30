package com.example.mykotlin

import android.app.Application
import androidx.multidex.MultiDex
import com.example.mykotlin.db.AppDatabase

class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);
    }

}