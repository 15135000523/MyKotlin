package com.example.mykotlin.textAnnotation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mykotlin.R;
import com.example.mykotlin.annotation.AnalysisAnna;
import com.example.mykotlin.annotation.InjectClick;
import com.example.mykotlin.annotation.InjectId;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TextAnnotationFragment extends Fragment {
    @InjectId(R.id.aaaa1)
    private TextView text1;
    @InjectId(R.id.aaaa2)
    private TextView text2;
    @InjectId(R.id.aaaa3)
    private TextView text3;
    @InjectId(R.id.aaaa4)
    private TextView text4;


    public TextAnnotationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_annotation, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AnalysisAnna.injectId(this);
        setText();
    }

    private void setText() {
        text1.setText("fragment文本1");
        text2.setText("fragment文本2");
        text3.setText("fragment文本3");
        text4.setText("fragment文本4");
    }
    @InjectClick({R.id.aaaa1,R.id.aaaa2,R.id.aaaa3,R.id.aaaa4})
    public void click(View v){
        if(v.getId()==R.id.aaaa1){
            text1.setText("换文本");
        }else if(v.getId()==R.id.aaaa2){
            text2.setText("换文本0");
        }else if(v.getId()==R.id.aaaa3){
            text3.setText("换文本++");
        }else{
            text4.setText("换文本---");
        }
    }

}