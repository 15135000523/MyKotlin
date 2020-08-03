package com.example.mykotlin.ui.main.fragment.my

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentMyBinding
import com.example.mykotlin.ui.smart.SmartActivity


class MyFragment : BaseFragment<MyViewModel,FragmentMyBinding>() {


    override fun initViewModel(): Class<MyViewModel> =MyViewModel::class.java
    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding= FragmentMyBinding.inflate(inflater,container,false)

    override fun initView() {
        mDataBinding.run {
            smart.setOnClickListener {
                startActivity(Intent(activity,SmartActivity::class.java))
            }
        }
    }
    override fun initObserver() {
    }



}