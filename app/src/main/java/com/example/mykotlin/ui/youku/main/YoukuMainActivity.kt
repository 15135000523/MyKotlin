package com.example.mykotlin.ui.youku.main

import androidx.fragment.app.Fragment
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityYoukuMainBinding
import com.example.mykotlin.ui.main.fragment.my.MyFragment
import com.example.mykotlin.ui.main.fragment.study.StudyFragment
import com.example.mykotlin.ui.youku.main.fragment.BannerFragment
import com.example.mykotlin.utils.StatusUtil

class YoukuMainActivity : BaseActivity<YoukuMainViewModel, ActivityYoukuMainBinding>() {

    private lateinit var adapter: FragmentAdapter
    private lateinit var list: ArrayList<Fragment>
    private val tabs = arrayOf("首页", "学习", "我的")
    override fun loadLayout(): Int = R.layout.activity_youku_main
    override fun initViewModel(): Class<YoukuMainViewModel> = YoukuMainViewModel::class.java

    override fun initView() {
        StatusUtil.setStatusBarColor(this, R.drawable.color_gradients)
        mViewModel.getOrderDetails("2020092002202348")

        initViewPager()
    }

    private fun initViewPager() {
        var binnerFragment = BannerFragment()
        var studyFragment = StudyFragment()
        var MyFragment = MyFragment()
        list = ArrayList()
        list.add(binnerFragment)
        list.add(studyFragment)
        list.add(MyFragment)
        adapter = FragmentAdapter(supportFragmentManager, list, tabs)
        mDataBinding.viewPager.adapter = adapter
        for (tab in tabs) {
            mDataBinding.toolbar.addTab(mDataBinding.toolbar.newTab().setText(tab))
        }
        mDataBinding.toolbar.setupWithViewPager(mDataBinding.viewPager)
    }

    override fun initObserver() {

    }


}