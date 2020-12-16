package com.example.mykotlin.ui.main.fragment.main

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import com.bigkoo.pickerview.TimePickerView
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseFragment
import com.example.mykotlin.bean.ReBean
import com.example.mykotlin.databinding.FragmentMainBinding
import com.example.mykotlin.login.UserBean
import com.example.mykotlin.utils.DateUtils
import com.example.mykotlin.utils.StatusUtil
import com.example.mykotlin.view.SelectorView


class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(),
    SelectorView.OnDataChangeListener, View.OnTouchListener {
    private lateinit var pvTime: TimePickerView
    private lateinit var windowManager: WindowManager
    private lateinit var layoutParams: WindowManager.LayoutParams
    private lateinit var textView: TextView

    override fun initViewModel(): Class<MainViewModel> = MainViewModel::class.java
    override fun loadLayout(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun initObserver() {}

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        createTimePicker()
        mDataBinding.run {
            mainName.setOnClickListener {
                pvTime.show(this.mainName)
                mDataBinding.refresh.isRefreshing = false
            }
        }
        mDataBinding.selectorView.setKilometreList(ArrayList<String>().apply {
            this.add("1KM")
            this.add("2KM")
            this.add("4KM")
            this.add("10KM")
            this.add("20KM")
            this.add("30KM")
            this.add("40KM")
        })
        mDataBinding.selectorView.setListener(this)
        mDataBinding.refresh.isEnabled = false
        activity?.getColor(R.color.Yellow)?.let { StatusUtil.setStatusBarColor(activity, it) }

        deskText()
        texteQuals()
    }

    private fun texteQuals(){
        var a = String(StringBuffer("abc"))
        var b = String(StringBuffer("abc"))
        Log.e("equals","a==b:"+(a==b))
        Log.e("equals","a===b:"+(a===b))
        var user = UserBean("闫文浩","10","男")
        var user1 = UserBean("闫文浩","10","男")
        Log.e("equals","user==user1:"+(user==user1))
        Log.e("equals","user===user1:"+(user===user1))
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

    override fun dataChangeListener(position: Int) {
        mDataBinding.refresh.isRefreshing = true
    }

    /**
     * 桌面显示文本
     */
    private fun deskText() {
        windowManager = activity?.getApplicationContext()
            ?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //设置TextView的属性
        layoutParams = WindowManager.LayoutParams()
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        //这里是关键，使控件始终在最上方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        }
        layoutParams.flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        layoutParams.gravity = Gravity.LEFT or Gravity.TOP

        textView = TextView(activity)
        textView.setText("周杰伦——青花瓷")
        textView.alpha = 1.0f
        textView.setBackgroundResource(R.color.whitef)
        textView.setTextColor(Color.BLACK)
        textView.setOnTouchListener(this)

        windowManager.addView(textView, layoutParams)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_UP -> {
                //getRawX/Y 是获取相对于Device的坐标位置 注意区别getX/Y[相对于View]
                layoutParams.x = event!!.rawX.toInt()
                layoutParams.y = event!!.rawY.toInt()
                //更新"桌面歌词"的位置
                windowManager.updateViewLayout(textView, layoutParams)
            }
            MotionEvent.ACTION_MOVE -> {
                layoutParams.x = event!!.rawX.toInt()
                layoutParams.y = event!!.rawY.toInt()
                windowManager.updateViewLayout(textView, layoutParams)
            }
        }
        return false

    }

}