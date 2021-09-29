package com.example.mykotlin.ui.youku.main.add

import android.app.Service
import android.content.Intent
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityAddBinding

class AddActivity : BaseActivity<AddViewMode, ActivityAddBinding>() {

    override fun loadLayout(): Int = R.layout.activity_add
    override fun initViewModel(): Class<AddViewMode> = AddViewMode::class.java

    override fun initView() {
        initClick()
        Intent()
    }

    private fun initClick() {
        mDataBinding.apply {
            tvMaterialSelect.setOnClickListener {//设备材质

            }
            tvNatureSelect.setOnClickListener { //设备性质

            }
            tvGtlxSelect.setOnClickListener { //杆塔类型

            }
            tvZcxzSelect.setOnClickListener { //资产性质

            }
            tvHlsSelect.setOnClickListener { //同杆架设回路数

            }
            tvTsdlSelect.setOnClickListener { //杆塔特殊地理

            }
            tvLxslSelect.setOnClickListener { //拉线数量

            }
            tvNatureSelect.setOnClickListener { //

            }
            tvNatureSelect.setOnClickListener { //

            }
            tvNatureSelect.setOnClickListener { //

            }
            tvNatureSelect.setOnClickListener { //

            }
            tvNatureSelect.setOnClickListener { //

            }
        }
    }

    override fun initObserver() {
    }

}