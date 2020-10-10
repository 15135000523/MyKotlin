package com.example.mykotlin.ui.main.fragment.my


import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startForegroundService
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentMyBinding
import com.example.mykotlin.service.ForegroundService
import com.example.mykotlin.utils.LocationUtils
import com.example.mykotlin.utils.WaterMarkSetting.Companion.createWatermarkBitMap
import java.lang.Exception
import java.lang.reflect.Field


class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    lateinit var mForegroundService: Intent;

    override fun initViewModel(): Class<MyViewModel> = MyViewModel::class.java
    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentMyBinding.inflate(inflater, container, false)

    override fun initView() {
        requireActivity().packageManager.getPackageInfo(
            requireActivity().packageName,
            PackageManager.GET_ACTIVITIES
        ).activities
            .filterNot { it.name == this::class.java.name }
            .filterNot { it.name == "leakcanary.internal.activity.LeakLauncherActivity" }
            .filterNot { it.name == "com.example.mykotlin.kotlin" }
            .filterNot { it.name == "com.example.mykotlin.ui.main.MainActivity" }
            .filterNot { it.name == "com.example.mykotlin.ui.medio.MediaSessionPlaybackActivity" }
            .filterNot { it.name == "com.example.mykotlin.lifecycle.LifecycleActivity" }
            .map {
                Class.forName(it.name)
            }
            .forEach { clazz ->

            }


//        setWaterMarkImage()
        getActivitys()
    }

    private fun getActivitys() {
        var info = requireActivity().packageManager.getPackageInfo(
            requireActivity().packageName,
            PackageManager.GET_ACTIVITIES
        )
        info.activities.filterNot { it.name == this::class.java.name }
            .filterNot { it.name == "leakcanary.internal.activity.LeakLauncherActivity" }
            .filterNot { it.name == "com.example.mykotlin.kotlin" }
            .filterNot { it.name == "com.example.mykotlin.ui.main.MainActivity" }
            .filterNot { it.name == "com.example.mykotlin.ui.medio.MediaSessionPlaybackActivity" }
            .filterNot { it.name == "com.example.mykotlin.lifecycle.LifecycleActivity" }.forEach {
                try {
                    mDataBinding.root.addView(AppCompatButton(requireContext()).apply {
                        isAllCaps = false
                        text = resources.getString(it.labelRes)
                        setOnClickListener (object :View.OnClickListener{
                            override fun onClick(v: View?) {
                                startActivity(Intent(requireContext(),Class.forName(it.name)))
                            }
                        })
                    })
                } catch (e: Exception) {

                }

            }
    }

    fun setWaterMarkImage() {
        Glide.with(this)
            .load("https://img-blog.csdnimg.cn/20190122173452703.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwMTE2NDE4,size_16,color_FFFFFF,t_70")
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    var map = activity?.let {
                        createWatermarkBitMap(
                            (resource as BitmapDrawable).bitmap,
                            "waterMark\n大厦或多个\n到货时间过的", it
                        )
                    }
                    Glide.with(this@MyFragment).load(map).into(mDataBinding.image)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    fun initService() {
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


    override fun initObserver() {
        LocationUtils.getInstance().create(activity)
    }


}