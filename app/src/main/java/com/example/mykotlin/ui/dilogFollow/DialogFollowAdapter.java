package com.example.mykotlin.ui.dilogFollow;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykotlin.R;
import com.example.mykotlin.bean.HomeBean;
import com.example.mykotlin.ui.smart.SmartAdapter;
import com.example.mykotlin.utils.DialogUtils;

import java.util.ArrayList;

public class DialogFollowAdapter extends RecyclerView.Adapter<DialogFollowAdapter.ViewHolder> {

    private ArrayList<HomeBean.DatasBean> mList;
    private Context mContext;
    private ItemClick click;
    private int oldSelect;

    public DialogFollowAdapter(Context mContext,ItemClick click) {
        this.mContext = mContext;
        this.click = click;
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
    public DialogFollowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_follow, parent, false);
        return new DialogFollowAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull DialogFollowAdapter.ViewHolder holder, int position) {
        holder.layout.setOnLongClickListener(view -> {
            click.onClick(view);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? 30: 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layout;
        private ImageView img;
        private TextView context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.constraintLayout);
            img = itemView.findViewById(R.id.image);
            context = itemView.findViewById(R.id.context);

        }
    }

    public interface ItemClick{
        void onClick(View view);
    }
}
