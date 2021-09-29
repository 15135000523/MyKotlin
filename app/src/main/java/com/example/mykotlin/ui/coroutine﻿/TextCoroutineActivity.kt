package com.example.mykotlin.ui.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlin.R
import kotlinx.coroutines.*

class TextCoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_coroutine)

//        text()
//        textGlobal()
//        Log.e("TextCoroutineActivity", "Hello")

        textGlobal1()
        for (i in 1..8) {
            Log.e("TextCoroutineActivity", "主线程打印第$i 次，时间:  ${System.currentTimeMillis()}")
        }
    }

    private fun textGlobal() = GlobalScope.launch {
        delay(1000L)
        Log.e("TextCoroutineActivity", "World!")
    }

    private fun textGlobal1() = GlobalScope.launch(Dispatchers.Unconfined) {
        for (i in 1..6) {
            Log.e("TextCoroutineActivity", "协程任务打印第$i 次，时间: ${System.currentTimeMillis()}")
        }
    }


    private fun text() = runBlocking {
        launch { doWorld() }
        Log.e("TextCoroutineActivity", "hello")
    }

    suspend fun doWorld(): String {
        delay(1000L)
        Log.e("TextCoroutineActivity", "World!")

        return "再见"
    }

}