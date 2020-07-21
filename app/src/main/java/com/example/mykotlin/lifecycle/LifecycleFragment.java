package com.example.mykotlin.lifecycle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mykotlin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LifecycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LifecycleFragment extends Fragment {

    private static LifecycleFragment fragment;
    private Location location;

    private LifecycleFragment() {
        location = new Location();
        getLifecycle().addObserver(location);
    }

    public static LifecycleFragment newInstance() {
        if (fragment==null){
            synchronized (LifecycleFragment.class){
                if (fragment==null){
                    fragment = new LifecycleFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }
}