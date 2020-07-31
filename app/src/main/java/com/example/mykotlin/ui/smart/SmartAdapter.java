package com.example.mykotlin.ui.smart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mykotlin.R;
import com.example.mykotlin.bean.HomeBean;

import java.util.ArrayList;

public class SmartAdapter extends RecyclerView.Adapter<SmartAdapter.ViewHolder> {

    private ArrayList<HomeBean.DatasBean> mList;
    private Context mContext;

    public SmartAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(ArrayList<HomeBean.DatasBean> mList) {
        this.mList.addAll(mList);
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_smart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(mContext).load(mList.get(position)).into(holder.img);
        holder.title.setText(mList.get(position).getTitle());
        holder.create.setText(mList.get(position).getNiceDate());
        holder.layout.setOnClickListener(view -> {
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layout;
        private ImageView img;
        private TextView title;
        private TextView create;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_smart);
            img = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            create = itemView.findViewById(R.id.create_time);
        }
    }
}
