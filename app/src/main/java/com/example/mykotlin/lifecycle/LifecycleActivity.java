package com.example.mykotlin.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mykotlin.R;

public class LifecycleActivity extends AppCompatActivity {

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        location = new Location();
        getLifecycle().addObserver(location);
    }
}