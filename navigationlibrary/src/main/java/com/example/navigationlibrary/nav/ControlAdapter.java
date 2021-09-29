package com.example.navigationlibrary.nav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.navigationlibrary.ControlBoardWindow;
import com.example.navigationlibrary.R;

import java.util.List;

public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.ControlHolder> {

    List<ControlBoardWindow.Twotxt> txtlist;

    public ControlAdapter(List<ControlBoardWindow.Twotxt> txtlist) {
        this.txtlist = txtlist;
    }

    @Override
    public ControlAdapter.ControlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ControlAdapter.ControlHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.control_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(ControlAdapter.ControlHolder holder, int position) {
        // Log.e("ControlBoardWindow","item set");
        holder.txt1.setText(txtlist.get(position).getTxt1());
        holder.txt2.setText(txtlist.get(position).getTxt2());
    }

    @Override
    public int getItemCount() {
        return txtlist.size();
    }

    public static class ControlHolder extends RecyclerView.ViewHolder {
        public TextView txt1;
        public TextView txt2;

        public ControlHolder(View v) {
            super(v);
            txt1 = v.findViewById(R.id.txt1);
            txt2 = v.findViewById(R.id.txt2);
        }
    }
}