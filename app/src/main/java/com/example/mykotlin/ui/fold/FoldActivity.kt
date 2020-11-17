package com.example.mykotlin.ui.fold

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityFold2Binding
import com.example.mykotlin.databinding.ActivityFoldBinding
import com.example.mykotlin.utils.FileUtils
import com.example.mykotlin.utils.GlideOptionsSetting
import com.example.mykotlin.utils.WaterMarkSetting.Companion.createWatermarkBitMap
import com.example.mykotlin.utils.WaterMarkSetting.Companion.saveAndCompress

class FoldActivity : BaseActivity<FoldViewModel, ActivityFoldBinding>() {
    override fun loadLayout(): Int = R.layout.activity_fold
    override fun initViewModel(): Class<FoldViewModel> = FoldViewModel::class.java

    override fun initView() {
        mDataBinding.apply {
        }
        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.car)
        saveAndCompress(
            bitmap, "水印\n2020-11-17 10:31:31",
            this,
            FileUtils.COMPRESS_PATH + "/1111.jpg",
            FileUtils.COMPRESS_PATH
        ) {
            Glide.with(this@FoldActivity).load(it).into(mDataBinding.contextImg1)
        }

    }

    override fun initObserver() {
    }


}