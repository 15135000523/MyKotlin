package com.example.mykotlin.ui.main.fragment.my


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startForegroundService
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentMyBinding
import com.example.mykotlin.service.ForegroundService
import com.example.mykotlin.ui.dilogFollow.DialogFollowActivity
import com.example.mykotlin.ui.medio.MediaActivity
import com.example.mykotlin.ui.smart.SmartActivity
import com.example.mykotlin.utils.GlideOptionsSetting
import com.example.mykotlin.utils.LocationUtils
import com.example.mykotlin.utils.WaterMarkSetting.Companion.createWatermarkBitMap


class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    lateinit var mForegroundService: Intent;

    override fun initViewModel(): Class<MyViewModel> = MyViewModel::class.java
    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentMyBinding.inflate(inflater, container, false)

    override fun initView() {
        mDataBinding.run {
            smart.setOnClickListener {
                startActivity(Intent(activity, SmartActivity::class.java))
            }
            follow.setOnClickListener {
                startActivity(Intent(activity, DialogFollowActivity::class.java))
            }
            service.setOnClickListener {
                //启动服务
                if (!ForegroundService.serviceIsLive) {
                    // Android 8.0使用startForegroundService在前台启动新服务
                    mForegroundService = Intent(context, ForegroundService::class.java)
                    mForegroundService.putExtra("Foreground", "This is a foreground service.")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context?.let { it1 -> startForegroundService(it1, mForegroundService) }
                    } else {
                        activity?.startService(mForegroundService)
                    }
                } else {
                    Toast.makeText(context, "前台服务正在运行中...", Toast.LENGTH_SHORT).show()
                }
            }
            video.setOnClickListener {
                startActivity(Intent(activity, MediaActivity::class.java))
            }
        }
        Glide.with(this).load("https://img-blog.csdnimg.cn/20190122173452703.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMTE2NDE4,size_16,color_FFFFFF,t_70")
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    var map = activity?.let {
                        createWatermarkBitMap(
                            (resource as BitmapDrawable).bitmap,
                            "waterMark\n大厦或多个\n到货时间过的", it) }
                    Glide.with(this@MyFragment).load(map).into(mDataBinding.image)
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })

    }

    override fun initObserver() {
        LocationUtils.getInstance().create(activity)
    }


}