package com.example.mykotlin.ui.smart

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.bean.HomeBean
import com.example.mykotlin.databinding.ActivitySmartBinding
import com.example.mykotlin.view.RecyclerDecoration
import kotlinx.coroutines.GlobalScope
import java.util.*

class SmartActivity : BaseActivity<SmartViewModel, ActivitySmartBinding>() {

    lateinit var adapter: SmartAdapter
    var page: Int = 0

    override fun loadLayout(): Int = R.layout.activity_smart
    override fun initViewModel(): Class<SmartViewModel>? = SmartViewModel::class.java

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
            recycler.layoutManager = GridLayoutManager(this@SmartActivity, 3)
//            recycler.layoutManager = StaggeredGridLayoutManager(3,RecyclerView.VERTICAL)
//            recycler.layoutManager = LinearLayoutManager(this@SmartActivity)
            recycler.addItemDecoration(RecyclerDecoration(this@SmartActivity, 20))
            adapter = SmartAdapter(this@SmartActivity).also { recycler.adapter = it }
        }
//        mViewModel.getService(page.toString())

        mViewModel.getWeather("太原")
    }

    @SuppressLint("ShowToast")
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
            mError.observe(this@SmartActivity, Observer {
                    Toast.makeText(this@SmartActivity, it, Toast.LENGTH_SHORT).show()
                })
            Transformations.map(mError) {
                it + " this is update data"
            }
            var bbb =
                Transformations.switchMap(mData, Function<HomeBean, LiveData<HomeBean.DatasBean>> {
                    liveData {
                        it.datas.get(0)
                    }
                })
        }
    }

}