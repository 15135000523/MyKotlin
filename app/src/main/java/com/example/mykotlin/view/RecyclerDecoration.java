package com.example.mykotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykotlin.R;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int bottomHeight;
    private Paint mPaint;
    private Rect mBounds = new Rect();

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RecyclerDecoration(Context mContext, int bottomHeight) {
        this.bottomHeight = bottomHeight;
        this.mContext = mContext;
        mPaint = new Paint();
        mPaint.setColor(mContext.getColor(R.color.Green));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = bottomHeight;
        outRect.right = 20;
        outRect.left = 20;
//        int position = parent.getChildLayoutPosition(view);
//        Log.e("-----","position:"+position+"取余："+position%3);
//        if(position%3==2){
//            outRect.right = 0;
//        }else{
//            outRect.right = 20;
//        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        drawVertical(c,parent);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        myDraw(c,parent);
    }
    private void myDraw(@NonNull Canvas c, @NonNull RecyclerView parent){
        c.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final int left = child.getPaddingLeft();
            final int right = child.getWidth()-child.getPaddingRight();
            //需要通过这个方法拿到子视图在canvas中的坐标
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            Log.e("myDraw","top:"+mBounds.top+"--bottom:"+mBounds.bottom);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            c.drawRect(left+20,bottom-20,right+20,bottom,mPaint);
        }
        c.restore();
    }
}
