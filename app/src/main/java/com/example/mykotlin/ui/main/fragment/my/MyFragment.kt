package com.example.mykotlin.ui.main.fragment.my


import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startForegroundService
import androidx.databinding.ViewDataBinding
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentMyBinding
import com.example.mykotlin.service.ForegroundService
import com.example.mykotlin.ui.dilogFollow.DialogFollowActivity
import com.example.mykotlin.ui.smart.SmartActivity
import com.example.mykotlin.utils.LocationUtils


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
        }

    }

    override fun initObserver() {
        LocationUtils.getInstance().create(activity)
    }


}