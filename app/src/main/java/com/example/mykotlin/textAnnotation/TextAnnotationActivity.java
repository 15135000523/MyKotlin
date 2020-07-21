package com.example.mykotlin.textAnnotation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mykotlin.R;
import com.example.mykotlin.ReBean;
import com.example.mykotlin.annotation.AnalysisAnna;
import com.example.mykotlin.annotation.InjectClick;
import com.example.mykotlin.annotation.injectId;
import com.example.mykotlin.lifecycle.LifecycleActivity;
import com.example.mykotlin.service.BaseResponse;
import com.example.mykotlin.service.DataService;
import com.example.mykotlin.service.IResponseCallBack;
import com.example.mykotlin.service.RetrofitUtil;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextAnnotationActivity extends AppCompatActivity {
    @injectId(R.id.annotation1)
    private TextView textView1;
    @injectId(R.id.annotation2)
    private EditText textView2;
    @injectId(R.id.annotation3)
    private ImageView textView3;
    @injectId(R.id.annotation4)
    private TextView textView4;
    @injectId(R.id.annotation5)
    private TextView textView5;
    @injectId(R.id.annotation6)
    private TextView textView6;
    @injectId(R.id.framelayout)
    private FrameLayout frameLayout;

    private TextAnnotationFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_annotation);
        AnalysisAnna.injectId(this);

        HashMap map = new HashMap();
        setText();
        fragment = new TextAnnotationFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.framelayout, fragment, "text").commit();
       // getData();
    }

    private void getData() {
        DataService service = RetrofitUtil.getInstance().create(DataService.class);
        Call<BaseResponse<String>> call = service.getAlreadyDoneList();
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                BaseResponse<String> baseResponse = response.body();
                Log.e("yan httpResponse","添加"+baseResponse.getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
            }
        });

    }

    private void setText() {
        textView1.setText("已经给成员变量赋值了");
        textView2.setText("这是个输入框");
        textView3.setImageResource(R.mipmap.ic_launcher);

        DataService dataService = RetrofitUtil.getInstance().create(DataService.class);
        RetrofitUtil.getInstance().invoke(dataService.getList(), new IResponseCallBack<ReBean>(){
            @Override
            public void onSuccess(ReBean reBean) {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    @InjectClick({R.id.annotation1, R.id.annotation2, R.id.annotation3})
    public void onClick(View v) {
        if (v.getId() == R.id.annotation1) {
           startActivity(new Intent(TextAnnotationActivity.this, LifecycleActivity.class));
        } else if (v.getId() == R.id.annotation2) {
            textView2.setText("点击这么猛烈");
        } else {
            textView3.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}