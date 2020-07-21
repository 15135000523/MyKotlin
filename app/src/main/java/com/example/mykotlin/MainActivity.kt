package com.example.mykotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bigkoo.pickerview.TimePickerView
import com.bigkoo.pickerview.TimePickerView.OnTimeSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(),Callback<String> {


    lateinit var pvTime: TimePickerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initHttp()
        main_name.setOnClickListener {
            pvTime.show(main_name);
        }
        pvTime = TimePickerView.Builder(this,
            OnTimeSelectListener { date, v -> //选中事件回调
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

    }
    fun initHttp(){
        var interceptor:LogInterceptor = LogInterceptor();
        var httpClient:OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        var retrofit:Retrofit =  Retrofit.Builder()
            .baseUrl("https://suggest.taobao.com/") //设置网络请求的Url地址
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .client(httpClient)
            .build();
        var request:GitHubService =retrofit.create(GitHubService::class.java);
        var call: Call<String> = request.getData();
        call.enqueue(this)
    }

    private fun getTimes(date: Date): String? { //可根据需要自行截取数据显示
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        Log.e("---3----","error:"+t.message.toString())
    }
    override fun onResponse(call: Call<String>, response: Response<String>) {
        Log.e("---4----","数据:"+response.body().toString())
    }
}
 interface GitHubService {
    @GET("/sug?code=utf-8&q=裤子&callback=cd")
    fun getData(): Call<String>;
}

