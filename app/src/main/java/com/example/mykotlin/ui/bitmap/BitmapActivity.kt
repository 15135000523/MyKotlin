package com.example.mykotlin.ui.bitmap

import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mykotlin.R
import com.example.mykotlin.base.BaseActivity
import com.example.mykotlin.bean.LoadTypeBean
import com.example.mykotlin.databinding.ActivityBitmapBinding
import com.example.mykotlin.viewFactory.ViewFactory
import java.io.UnsupportedEncodingException

class BitmapActivity : BaseActivity<BitmapViewmodel, ActivityBitmapBinding>() {

    private var loadList = ArrayList<LoadTypeBean>()

    override fun loadLayout(): Int = R.layout.activity_bitmap
    override fun initViewModel(): Class<BitmapViewmodel> = BitmapViewmodel::class.java

    @ExperimentalStdlibApi
    override fun initView() {
//        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.car)
//        var outPut = BufferedOutputStream(FileOutputStream(File(FileUtils.COMPRESS_PATH + "/1111.jpeg")))
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outPut)
        createLoadRule()
        addView()

        base64decodeOrencode("闫文浩你好")
    }

    override fun initObserver() {
    }

    private fun createLoadRule() {
        for (i in 0..30) {
            when (i % 4) {
                0 -> {
                    loadList.add(LoadTypeBean("设备类型" + i, "提示语" + i, "非空", "输入框"))
                }
                1 -> {
                    loadList.add(LoadTypeBean("设备类型" + i, "提示语" + i, "非空", "一级弹窗"))
                }
                2 -> {
                    loadList.add(LoadTypeBean("设备类型" + i, "提示语" + i, "非空", "输入数字"))
                }
                3 -> {
                    loadList.add(LoadTypeBean("设备类型" + i, "提示语" + i, "非空", "输入弹窗"))
                }
            }
        }
    }

    private fun addView() {
        for (i in loadList.indices) {
            mDataBinding.layout.addView(ViewFactory.getInstance(this, loadList.get(i)))
        }
    }

    /**
     * 验证内容是否为空
     */
    private fun isOk(view: ViewGroup): Boolean {
        for (i in 0 until view.childCount) {
            when (view.getChildAt(i)) {
                is LinearLayout -> isOk(view.getChildAt(i) as LinearLayout)
                is RelativeLayout -> isOk(view.getChildAt(i) as RelativeLayout)
                is ConstraintLayout -> isOk(view.getChildAt(i) as ConstraintLayout)
            }
            if (view.getChildAt(i).tag == "1") {
                when (view.getChildAt(i)) {
                    is TextView -> {
                        var text = view.getChildAt(i) as TextView
                        if (TextUtils.isEmpty(text.text)) {
                            Toast.makeText(this, text.hint, Toast.LENGTH_SHORT).show()
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    /**
     * 非文本数据称为二进制数据
     * 一个字节8位
     * a-z A-Z 0-9 /+
     *
     * base64是6位一个字符
     *
     * 变种base58 去掉了Il 0o /+  去掉/+为了方便复制，去掉Il 0o为了防止混淆，认错
     * 用于各种币种的地址
     *
     * 序列化是将数据对象(内存中的数据)转换成字节序列的过程
     * 反序列化是将字节序列转换成数据对象(内存中的数据)
     * 目的：让内存中的对象可以传输存储
     */
    private fun base64decodeOrencode(str: String) {
        var aa: ByteArray? = null
        try {
            aa = str.toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        val base =
            Base64.encodeToString(aa, Base64.DEFAULT)
        val bb = Base64.decode(base, Base64.DEFAULT)
        Log.e("base64", "aa base64 原字符:$base")
        Log.e("base64", "aa base64:$base")
        Log.e("base64", "aa base64 解码：" + String(bb))
    }
}