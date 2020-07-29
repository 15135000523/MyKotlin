package com.example.mykotlin.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mykotlin.R
import com.example.mykotlin.service.DataService
import com.example.mykotlin.service.IResponseCallBack
import com.example.mykotlin.service.RetrofitUtil
import com.example.mykotlin.ui.ReBean
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragmet)
        NavigationUI.setupWithNavController(main_bottom,navController )

        initHttp()
    }

    fun initHttp() {
        var request: DataService = RetrofitUtil.getInstance().create(
            DataService::class.java
        );
        RetrofitUtil.getInstance().invoke(request.list, object : IResponseCallBack<ReBean> {
            override fun onSuccess(t: ReBean) {
                Log.e("---4----", "数据:" + t.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("---3----", "error:" + e.message.toString())
            }

        })
    }
}


