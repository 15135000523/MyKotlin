package com.example.mykotlin.ui.fold

import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityFold2Binding
import com.example.mykotlin.databinding.ActivityFoldBinding

class FoldActivity : BaseActivity<FoldViewModel, ActivityFold2Binding>() {
    override fun loadLayout(): Int = R.layout.activity_fold2
    override fun initViewModel(): Class<FoldViewModel> = FoldViewModel::class.java

    override fun initView() {
        mDataBinding.apply {
            mDataBinding.toolbar
        }
    }

    override fun initObserver() {
    }



}