package com.example.mykotlin.ui.fold

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityFold2Binding
import com.example.mykotlin.databinding.ActivityFoldBinding
import com.example.mykotlin.utils.GlideOptionsSetting
import com.example.mykotlin.utils.WaterMarkSetting.Companion.createWatermarkBitMap

class FoldActivity : BaseActivity<FoldViewModel, ActivityFoldBinding>() {
    override fun loadLayout(): Int = R.layout.activity_fold
    override fun initViewModel(): Class<FoldViewModel> = FoldViewModel::class.java

    override fun initView() {
        mDataBinding.apply {
        }

        Glide.with(this).load(R.mipmap.car).apply(GlideOptionsSetting.getDefaultOptions())
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    var map = createWatermarkBitMap(
                        (resource as BitmapDrawable).bitmap,
                        "waterMark",
                        this@FoldActivity
                    )
                    Glide.with(this@FoldActivity).load(map).into(mDataBinding.contextImg1)
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    override fun initObserver() {
    }


}