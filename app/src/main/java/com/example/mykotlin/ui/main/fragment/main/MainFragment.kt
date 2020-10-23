package com.example.mykotlin.ui.main.fragment.main

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import com.bigkoo.pickerview.TimePickerView
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.databinding.FragmentMainBinding
import com.example.mykotlin.utils.DateUtils
import com.example.mykotlin.utils.StatusUtil


class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    lateinit var pvTime: TimePickerView

    override fun initViewModel(): Class<MainViewModel> = MainViewModel::class.java
    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        return FragmentMainBinding.inflate(inflater,container,false)
    }

    override fun initObserver() {
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        createTimePicker();
        mDataBinding.run {
            mainName.setOnClickListener {
                pvTime.show(this.mainName)
            }
        }
        mDataBinding.selectorView.setKilometreList(ArrayList<String>().apply {
            this.add("1KM")
            this.add("2KM")
            this.add("4KM")
        })
        activity?.getColor(R.color.Yellow)?.let { StatusUtil.setStatusBarColor(activity, it) }
    }

    /**
     * 创建
     */
    private fun createTimePicker() {
        pvTime = TimePickerView.Builder(activity,
            TimePickerView.OnTimeSelectListener { date, v -> //选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                val btn = v as TextView
                btn.setText(DateUtils.dateToString(date))
            }) //年月日时分秒 的显示与否，不设置则默认全部显示
            .setType(booleanArrayOf(true, true, true, true, true, false))
            .setLabel("年", "月", "日", "时", "", "")
            .isCenterLabel(true)
            .setContentSize(21)
            .setDecorView(null)
            .build()
    }




}