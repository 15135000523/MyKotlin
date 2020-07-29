package com.example.mykotlin.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bigkoo.pickerview.TimePickerView
import com.example.mykotlin.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.main_name
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {
    lateinit var pvTime: TimePickerView

    private lateinit var contentView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(R.layout.fragment_main, container, false)
        contentView.main_name.setOnClickListener {
            pvTime.show(main_name);
        }
        pvTime = TimePickerView.Builder(activity,
            TimePickerView.OnTimeSelectListener { date, v -> //选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                val btn = v as TextView
                btn.setText(getTimes(date))
            }) //年月日时分秒 的显示与否，不设置则默认全部显示
            .setType(booleanArrayOf(true, true, true, true, true, false))
            .setLabel("年", "月", "日", "时", "", "")
            .isCenterLabel(true)
            .setContentSize(21)
            .setDecorView(null)
            .build()
        return contentView
    }



    private fun getTimes(date: Date): String? { //可根据需要自行截取数据显示
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }

}