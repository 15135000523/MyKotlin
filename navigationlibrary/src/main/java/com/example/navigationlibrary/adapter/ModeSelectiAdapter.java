package com.example.navigationlibrary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationlibrary.R;
import com.example.navigationlibrary.bean.ModeSelectBean;

import java.util.ArrayList;
import java.util.List;

public class ModeSelectiAdapter extends RecyclerView.Adapter<ModeSelectiAdapter.ViewHolder> {

    private List<ModeSelectBean> mList;
    private Context mContext;
    private int selectPosition = 0;
    private IModeSelectListener listener;

    public ModeSelectiAdapter(List<ModeSelectBean> mList, Context mContext) {
        this.mList = new ArrayList<>();
        this.mList.addAll(mList);
        this.mContext = mContext;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public IModeSelectListener getListener() {
        return listener;
    }

    public void setListener(IModeSelectListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_nav_type, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.item_btn_type);
        }

        @SuppressLint("ResourceAsColor")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void bind() {
            type.setText(mList.get(getAdapterPosition()).getType());
            Drawable drawable = mContext.getResources().getDrawable(mList.get(getAdapterPosition()).getMinmap(), null);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (selectPosition == getAdapterPosition()) {
                type.setTextColor(R.color.mode_select);
                drawable.setTint(R.color.mode_select);
            } else {
                type.setTextColor(R.color.mode_select_no);
                drawable.setTint(R.color.mode_select_no);
            }
            type.setCompoundDrawables(drawable, null, null, null);
            type.setOnClickListener(v -> {
                if (listener != null) {
                    listener.click(getAdapterPosition());
                }
            });
        }
    }


}
