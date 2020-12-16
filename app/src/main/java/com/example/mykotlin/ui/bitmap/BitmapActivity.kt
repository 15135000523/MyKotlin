package com.example.mykotlin.ui.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityBitmapBinding
import com.example.mykotlin.utils.FileUtils
import com.example.mykotlin.utils.compressImg
import java.io.*
import java.util.*
import kotlin.collections.HashMap

class BitmapActivity : BaseActivity<BitmapViewmodel, ActivityBitmapBinding>() {


    override fun loadLayout(): Int = R.layout.activity_bitmap
    override fun initViewModel(): Class<BitmapViewmodel> = BitmapViewmodel::class.java

    override fun initView() {
//        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.car)
//        var outPut = BufferedOutputStream(FileOutputStream(File(FileUtils.COMPRESS_PATH + "/1111.jpeg")))
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outPut)
    }

    override fun initObserver() {
        Thread.currentThread()
        var map = HashMap<String,String>()
        map.set("nin","ishdhj")
    }


}