package com.example.mykotlin.ui.youku.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentBinnerBinding



class BannerFragment : BaseFragment<BannerViewModel, FragmentBinnerBinding>() {

    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentBinnerBinding.inflate(inflater, container, false)

    override fun initViewModel(): Class<BannerViewModel> = BannerViewModel::class.java

    override fun initView() {
    }

    override fun initObserver() {
    }


}