package com.example.mykotlin.ui.smart

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.bean.HomeBean
import com.example.mykotlin.databinding.ActivitySmartBinding
import com.example.mykotlin.view.RecyclerDecoration
import java.util.*

class SmartActivity : BaseActivity<SmartViewModel, ActivitySmartBinding>() {

    lateinit var adapter: SmartAdapter
    var page: Int = 0

    override fun loadLayout(): Int = R.layout.activity_smart
    override fun initViewModel(): SmartViewModel = SmartViewModel()

    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        mDataBinding.run {
            smartRefresh.setEnableAutoLoadMore(false)
            smartRefresh.setFooterTriggerRate(0.8f)
            smartRefresh.setOnRefreshListener {
                adapter.clearList()
                page = 0
                mViewModel.getService(page.toString())
            }
            smartRefresh.setOnLoadMoreListener {
                page++
                mViewModel.getService(page.toString())
            }
//            DividerItemDecoration(this@SmartActivity, DividerItemDecoration.VERTICAL)
//            recycler.layoutManager = GridLayoutManager(this@SmartActivity, 3)
//            recycler.layoutManager = StaggeredGridLayoutManager(3,RecyclerView.VERTICAL)
            recycler.layoutManager = LinearLayoutManager(this@SmartActivity)
            recycler.addItemDecoration(RecyclerDecoration(this@SmartActivity, 20))
            adapter = SmartAdapter(this@SmartActivity).also { recycler.adapter = it }
        }
        mViewModel.getService(page.toString())
    }

    override fun initObserver() {
        mViewModel.run {
            mData.observe(this@SmartActivity, Observer {
                if (page == 0) {
                    mDataBinding.smartRefresh.finishRefresh(true)
                } else {
                    mDataBinding.smartRefresh.finishLoadMore(true)
                }
                adapter.setmList(it.datas as ArrayList<HomeBean.DatasBean>)
                adapter.notifyDataSetChanged()
            })
        }
    }

}