package com.example.mykotlin.ui.morelayout

import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.mykotlin.R
import com.example.mykotlin.workmanager.UpLoadLogWorker
import kotlinx.android.synthetic.main.activity_more_layout.*
import java.io.File
import java.util.concurrent.TimeUnit

class MoreLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_layout)

        initWorker()
        initpdf()
    }

    private fun initpdf() {
        var descriptor = ParcelFileDescriptor.open(
            File(Environment.getExternalStorageDirectory().absolutePath + "/bbb.pdf"),
            ParcelFileDescriptor.MODE_READ_ONLY
        )
        var a = PdfRenderer(descriptor)
        var page = a.openPage(0)
        val bitmap: Bitmap =
            Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        image.setImageBitmap(bitmap)
        page.close()
        a.close()
    }

    private fun initWorker() {
        //充电状态，网络已连接，电池电量充足
        var constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        var data = Data.Builder().putString("name", "yan").build()
        var work = OneTimeWorkRequest.Builder(UpLoadLogWorker::class.java)
            .addTag("upload")
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        var work2 = OneTimeWorkRequest.Builder(UpLoadLogWorker::class.java)
            .setInitialDelay(10, TimeUnit.SECONDS)//延迟10秒执行
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .addTag("uploadworker")
            .build()
        WorkManager.getInstance(this).enqueue(work)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(work.id).observe(this, Observer {
            if (it.state == WorkInfo.State.SUCCEEDED)
                it.outputData.getString("name")
        })
        WorkManager.getInstance(this).cancelAllWorkByTag("upload")
    }
}
