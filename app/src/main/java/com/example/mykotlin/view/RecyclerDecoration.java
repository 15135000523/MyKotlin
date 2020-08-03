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
        int position = parent.getChildLayoutPosition(view);
        Log.e("-----","position:"+position+"取余："+position%3+",-总条数:"+(parent.getChildCount()-1));
        if(position == parent.getChildCount()-1){
            outRect.bottom = 0;
        }else{
            outRect.bottom = 20;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        drawVertical(c,parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        myDraw(c,parent);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void myDraw(@NonNull Canvas c, @NonNull RecyclerView parent){
        c.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++) {
            final View child = parent.getChildAt(i);
            final int left = child.getPaddingLeft();
            final int right = child.getWidth()-child.getPaddingRight();
            //需要通过这个方法拿到子视图在canvas中的坐标
            parent.getDecoratedBoundsWithMargins(child, mBounds);
//            Log.e("myDraw","top:"+mBounds.top+"--bottom:"+mBounds.bottom);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            mPaint.setColor(mContext.getColor(R.color.Green));
            c.drawRect(mBounds.left+20,bottom-20,mBounds.right-20,bottom,mPaint);
            if (i%2==0){
                mPaint.setColor(mContext.getColor(R.color.Yellow));
                c.drawRect(left,mBounds.top,left+20,mBounds.bottom-20,mPaint);
            }else{
                mPaint.setColor(mContext.getColor(R.color.Orange));
                c.drawRect(mBounds.right-20,mBounds.top,mBounds.right,mBounds.bottom-20,mPaint);
            }
        }
        c.restore();
    }
}
