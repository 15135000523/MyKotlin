package com.example.mykotlin

import android.app.Application
import androidx.multidex.MultiDex
import com.example.navigationlibrary.initializer.NavInitializer
import com.tencent.mmkv.MMKV


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        MMKV.initialize(this)

        NavInitializer.init(this)
    }


}