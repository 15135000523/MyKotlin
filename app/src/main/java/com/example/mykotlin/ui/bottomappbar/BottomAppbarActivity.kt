package com.example.mykotlin.ui.bottomappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityBottomAppbarBinding

class BottomAppbarActivity : BaseActivity<BottomAppbarViewModel, ActivityBottomAppbarBinding>() {

    override fun loadLayout(): Int = R.layout.activity_bottom_appbar
    override fun initViewModel(): Class<BottomAppbarViewModel> = BottomAppbarViewModel::class.java
    override fun initObserver() {
    }

    override fun initView() {
        mDataBinding.apply {
            bottomAppBar.replaceMenu(R.menu.my_menu)
        }
    }


}