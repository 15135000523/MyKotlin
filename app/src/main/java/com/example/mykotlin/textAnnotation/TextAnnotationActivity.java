package com.example.mykotlin.textAnnotation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mykotlin.R;
import com.example.mykotlin.annotation.AnalysisAnna;
import com.example.mykotlin.annotation.InjectClick;
import com.example.mykotlin.annotation.InjectId;
import com.example.mykotlin.lifecycle.LifecycleActivity;

import java.util.HashMap;


public class TextAnnotationActivity extends AppCompatActivity {
    @InjectId(R.id.annotation1)
    private TextView textView1;
    @InjectId(R.id.annotation2)
    private EditText textView2;
    @InjectId(R.id.annotation3)
    private ImageView textView3;
    @InjectId(R.id.annotation4)
    private TextView textView4;
    @InjectId(R.id.annotation5)
    private TextView textView5;
    @InjectId(R.id.annotation6)
    private TextView textView6;
    @InjectId(R.id.framelayout)
    private FrameLayout frameLayout;

    private TextAnnotationFragment fragment;
    private Handler handler = new Handler((message)->{
        return false;
    });

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
    }


    private void setText() {
        textView1.setText("已经给成员变量赋值了");
        textView2.setText("这是个输入框");
        textView3.setImageResource(R.mipmap.ic_launcher);

    }

    @InjectClick({R.id.annotation1, R.id.annotation2, R.id.annotation3})
    public void onClick(View v) {
        if (v.getId() == R.id.annotation1) {
            startActivity(new Intent(TextAnnotationActivity.this, LifecycleActivity.class));
        } else if (v.getId() == R.id.annotation2) {
            textView2.setText("点击了第二个");
        } else {
            textView3.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}