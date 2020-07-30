package com.example.mykotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykotlin.R;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int bottomHeight;
    private Paint mPaint;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RecyclerDecoration(Context mContext, int bottomHeight) {
        this.bottomHeight = bottomHeight;
        this.mContext = mContext;
        mPaint = new Paint();
        mPaint.setColor(mContext.getColor(R.color.black));
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = bottomHeight;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
