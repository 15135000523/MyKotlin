package com.example.mykotlin.ui.youku.main

import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityYoukuMainBinding

class YoukuMainActivity : BaseActivity<YoukuMainViewModel, ActivityYoukuMainBinding>() {
    override fun loadLayout(): Int = R.layout.activity_youku_main
    override fun initViewModel(): Class<YoukuMainViewModel> = YoukuMainViewModel::class.java

    override fun initObserver() {
    }

    override fun initView() {
    }
}