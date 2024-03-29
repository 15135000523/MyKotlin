package com.example.mykotlin.http;

import android.util.Log;

import com.example.mykotlin.utils.FileUtils;

import java.io.File;
import java.io.IOException;
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

//        public static final String BASE_URL = "https://wanandroid.com/";
    public static final String BASE_URL = "https://free-api.heweather.net/";
//    public static final String BASE_URL = "https://s2.gjdwllgdgs.com:30001/pwkshApp/";


    public final long OUT_TIME = 10 * 1000;
    public final long CACHE_MAX_SIZE = 1024 * 1024 * 10;
    public final String CACHE_FILE_PATH = FileUtils.getExternalStoragePath() + "/cache/mykotlin";

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
                    .cache(new Cache(createFile(), CACHE_MAX_SIZE))
                    .sslSocketFactory(HttpsSSL.createSSLSocketFactory(), new HttpsSSL())
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
        return retrofit().create(service);
    }

    /**
     * 使用rxjava
     * 此方法没有提供解析code值
     *
     * @param t        接口返回的类型
     * @param callBack 用于将返回的数据回掉出去
     * @param <T>      具体的数据类型
     */
    public <T> void invoke(Observable<T> t, ICallback<T> callBack) {
        t.subscribeOn(Schedulers.io())
                .doOnSubscribe(onSubscribe -> Log.v("yan httpRequest", "开始请求网络数据:"))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> Log.v("yan httpResponse", "数据请求结束"))
                .subscribe(new DefaultObserver<T>() {
                    @Override
                    public void onNext(T response) {
                        callBack.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 使用rxjava
     * 这个方法是有baseResponse，集中处理了code值，只返回code=0的data，
     *
     * @param t        接口返回的类型
     * @param callBack 用于将返回的数据回掉出去
     * @param <T>      具体的数据类型
     */
    public <T> void invoke(Observable<BaseResponse<T>> t, IBaseResponseCallBack<T> callBack) {
        t.subscribeOn(Schedulers.io())
                .doOnSubscribe(onSubscribe -> Log.e("yan httpRequest", "开始请求网络数据:"))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> Log.e("yan httpResponse", "数据请求结束"))
                .subscribe(new DefaultObserver<BaseResponse<T>>() {
                    @Override
                    public void onNext(BaseResponse<T> response) {
                        if (isSuccess(response.getCode()).equals("0")) {
                            callBack.onSuccess(response);
                        } else {
                            callBack.onError(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 使用rxjava
     * 这个方法是有baseResponse，集中处理了code值，只返回code=0的data，
     * IOnRequest可以在请求开始前和结束时加一些额外的操作
     *
     * @param t        接口返回的类型
     * @param callBack 用于将返回的数据回掉出去
     * @param <T>      具体的数据类型
     */
    public <T> void invoke(Observable<BaseResponse<T>> t, IBaseResponseCallBack<T> callBack, IOnRequest onRequest) {
        t.subscribeOn(Schedulers.io())
                .doOnSubscribe(onSubscribe -> onRequest.requestBegin())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> onRequest.requestEnd())
                .subscribe(new DefaultObserver<BaseResponse<T>>() {
                    @Override
                    public void onNext(BaseResponse<T> response) {
                        if (isSuccess(response.getCode()).equals("0")) {
                            callBack.onSuccess(response);
                        } else {
                            callBack.onError(response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private File createFile() {
        File file = new File(CACHE_FILE_PATH);
        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * ARouter
     * 跟据对应的code值解析对应的数据
     *
     * @param code
     * @return
     */
    private String isSuccess(String code) {
        if (code.equals("0")) {
            return "0";
        } else if (code.equals("100")) {
            return "没有更多数据";
        } else if (code.equals("102")) {
            return "102";
        } else {
            return "其他错误";
        }

    }
}
