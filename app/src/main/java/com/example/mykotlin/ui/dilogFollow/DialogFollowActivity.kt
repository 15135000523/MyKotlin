package com.example.mykotlin.ui.dilogFollow

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.databinding.ActivityDialogFollowBinding
import com.example.mykotlin.utils.DialogUtils

class DialogFollowActivity : BaseActivity<DialogFollowViewModel, ActivityDialogFollowBinding>() {

    lateinit var adapter: DialogFollowAdapter

    override fun initViewModel(): Class<DialogFollowViewModel> = DialogFollowViewModel::class.java
    override fun loadLayout(): Int = R.layout.activity_dialog_follow

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        mDataBinding.run {
            recycler.layoutManager = GridLayoutManager(this@DialogFollowActivity, 3)
            adapter = DialogFollowAdapter(this@DialogFollowActivity, {
                hiView.visibility = View.VISIBLE
                hiView.setBackgroundColor(getColor(R.color.YellowAL))
                it.setBackgroundColor(getColor(R.color.white))
                DialogUtils.getInstance().createDialog(this@DialogFollowActivity, it)
            }).also { recycler.adapter = it }
        }
    }

    override fun initObserver() {
    }


}