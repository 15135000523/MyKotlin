package com.example.mykotlin.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.mykotlin.R;
import com.example.mykotlin.textAnnotation.TextAnnotationActivity;
import com.example.mykotlin.ui.morelayout.MoreLayoutActivity;

public class LifecycleActivity extends AppCompatActivity {

    private Location location;
    private DrawerLayout drawerLayout;
    private TextView open;
    private TextView close;

    private String[] groups = {"采集", "巡检", "配网可视化", "设备退役"};

    private String[][] childs = {{"设备普查", "运城采集", "临汾采集", "晋城采集"}, {"介休设备巡检", "大同设备巡检", "运城设备巡检"}, {"配网可视化"}, {"设备退役"}};

    private ExpandableListView expandableListView;
    private ExpandableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        location = new Location();
        getLifecycle().addObserver(location);

        initView();
        initAdapter();
    }

    private void initAdapter() {
        expandableListView = findViewById(R.id.expand_list);
        adapter = new ExpandableAdapter(groups, childs, this);
        expandableListView.setAdapter(adapter);
        //展开第一条
        expandableListView.expandGroup(0);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Log.e("expandableListView", " onGroupClick " + groups[groupPosition]);
                return false;
            }
        });
        //子视图的点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                startActivity(new Intent(LifecycleActivity.this, MoreLayoutActivity.class));
                Log.e("expandableListView", " onChildClick " + childs[groupPosition][childPosition]);
                return true;
            }
        });
        //用于当组项折叠时的通知。
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.e("expandableListView", " onGroupCollapse 组折叠 " + groups[groupPosition]);
            }
        });
        //用于当组项折叠时的通知。
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.e("expandableListView", " onGroupCollapse 展开组 " + groups[groupPosition]);
            }
        });
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer);
        open = findViewById(R.id.open);
        close = findViewById(R.id.close);

        open.setOnClickListener(v -> {
            drawerLayout.openDrawer(Gravity.LEFT);
        });

        close.setOnClickListener(v -> {
            drawerLayout.closeDrawer(Gravity.LEFT);
        });

    }
}