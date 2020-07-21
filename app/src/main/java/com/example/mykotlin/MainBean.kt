package com.example.mykotlin

import android.content.SharedPreferences
import android.util.Log

data class MainBean(var name:String,var sex:String,var aihao:String,var jianjie:String)

fun MainBean.goPackBefore(){
    Log.e("------","我把mainbean存在了shared")
}