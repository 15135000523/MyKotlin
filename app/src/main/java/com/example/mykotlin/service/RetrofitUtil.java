package com.example.mykotlin.service;

import android.app.AppOpsManager;
import android.util.Log;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    public static final String BASE_URL = "https://suggest.taobao.com/";
    public final long OUT_TIME = 10 * 1000;

    public static RetrofitUtil retrofitUtil;

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private RetrofitUtil() {
    }

    public synchronized static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .callTimeout(OUT_TIME, TimeUnit.SECONDS)
                    .cache(new Cache(new File(""),1024*1024))
                    .sslSocketFactory(HttpsSSL.createSSLSocketFactory(),new HttpsSSL())
                    .addInterceptor(new LogInterceptor.Builder().build())
                    .build();
        }
        return okHttpClient;
    }

    private synchronized Retrofit retrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .build();
        }
        return retrofit;
    }

    public <Q> Q create(Class<Q> service) {
        Q q = retrofit().create(service);
        return q;
    }

    /**
     * 使用rxjava
     *
     * @param t        接口返回的类型
     * @param callBack 用于将返回的数据回掉出去
     * @param <T>      具体的数据类型
     */
    public <T> void invoke(Observable<T> t, IResponseCallBack<T> callBack) {
        t.subscribeOn(Schedulers.io())
                .doOnSubscribe(onSubscribe-> Log.e("yan httpRequest","开始请求网络数据:"))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()-> Log.e("yan httpResponse","数据请求结束"))
                .subscribe(new DefaultObserver<T>() {
                    @Override
                    public void onNext(T response) {
                        callBack.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() { }
                });
    }

}
