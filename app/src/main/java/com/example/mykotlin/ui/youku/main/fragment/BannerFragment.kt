package com.example.mykotlin.ui.youku.main.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentBinnerBinding
import com.example.mykotlin.ui.MyAdapter
import com.example.mykotlin.ui.youku.main.add.AddActivity


class BannerFragment : BaseFragment<BannerViewModel, FragmentBinnerBinding>() {

    private var list =
        arrayOf("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")

    lateinit var adapter: MyAdapter

    override fun loadLayout(): Int = R.layout.fragment_binner

    override fun initViewModel(): Class<BannerViewModel> = BannerViewModel::class.java

    override fun initView() {
        adapter = MyAdapter(list) {
            startActivity(Intent(activity, AddActivity::class.java))
        }
        mDataBinding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mDataBinding.recycler.adapter = adapter
    }

    override fun initObserver() {
    }


}