package com.example.mykotlin.utils

import okhttp3.*
import java.util.concurrent.TimeUnit

class HttpLoggingInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MyChain :Interceptor.Chain{
        override fun writeTimeoutMillis(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun call(): Call {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun proceed(request: Request): Response {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun withWriteTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun connectTimeoutMillis(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun connection(): Connection? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun withConnectTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun withReadTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun request(): Request {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun readTimeoutMillis(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

}