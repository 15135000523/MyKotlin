package com.example.mykotlin.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UpLoadLogWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        inputData.getString("name")
        upLog()
        val data = Data.Builder().putString("name", "success").build()
        return Result.success(data)
    }

    private fun upLog() {
    }
}