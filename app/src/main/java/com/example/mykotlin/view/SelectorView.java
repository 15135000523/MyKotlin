package com.example.mykotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mykotlin.R;

import java.util.List;

public class SelectorView extends View {

    private Paint paint;

    private int lineStartSelectorColor;
    private int lineMiddleSelectorColor;
    private int lineEndSelectorColor;
    //分割线颜色
    private int lineDivisionColor = 0xffffffff;
    //分割线宽度
    private int lineDivisionWidth;
    //字体颜色
    private int textColor = 0xff000000;

    //分段宽度
    private int subsectionWidth;
    //分段高度
    private int subsectionHeight;

    //小球移动的X轴
    private float cx;
    //数据集
    private List<String> kilometreList;

    private OnDataChangeListener listener;

    public SelectorView(Context context) {
        this(context, null);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        lineStartSelectorColor = array.getColor(R.styleable.SelectorView_lineStartSelectorColor, 0xFF7BF6D2);
        lineEndSelectorColor = array.getColor(R.styleable.SelectorView_lineEndSelectorColor, 0xFF0DAB7D);
        lineMiddleSelectorColor = (lineStartSelectorColor + lineEndSelectorColor) / 2;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAlpha(255);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        subsectionHeight = getBottom() / 4;
        subsectionWidth = getRight() / 3;
        lineDivisionWidth = (int) (subsectionWidth * 0.06);
        cx = subsectionWidth - lineDivisionWidth / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        paint.setStrokeWidth(1);

        paint.setColor(lineDivisionColor);
        canvas.drawCircle(cx, subsectionHeight / 4, subsectionHeight / 4, paint);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(subsectionHeight / 2);
        if (kilometreList != null) {
            canvas.drawText(kilometreList.get(0), subsectionWidth / 2, subsectionHeight * 2 - subsectionHeight / 2, paint);
            canvas.drawText(kilometreList.get(1), subsectionWidth / 2 + subsectionWidth, subsectionHeight * 2 - subsectionHeight / 2, paint);
            canvas.drawText(kilometreList.get(2), subsectionWidth / 2 + subsectionWidth * 2, subsectionHeight * 2 - subsectionHeight / 2, paint);
        }
    }

    private void drawLine(Canvas canvas) {
        paint.setStrokeWidth(subsectionHeight);

        paint.setColor(lineStartSelectorColor);
        canvas.drawLine(0, 0, subsectionWidth - lineDivisionWidth, 0, paint);
        paint.setColor(lineDivisionColor);
        canvas.drawLine(subsectionWidth - lineDivisionWidth, 0, subsectionWidth, 0, paint);


        paint.setColor(lineMiddleSelectorColor);
        canvas.drawLine(subsectionWidth, 0, subsectionWidth * 2 - lineDivisionWidth, 0, paint);
        paint.setColor(lineDivisionColor);
        canvas.drawLine(subsectionWidth * 2 - lineDivisionWidth, 0, subsectionWidth * 2, 0, paint);


        paint.setColor(lineEndSelectorColor);
        canvas.drawLine(subsectionWidth * 2, 0, subsectionWidth * 3 - lineDivisionWidth, 0, paint);
        paint.setColor(lineDivisionColor);
        canvas.drawLine(subsectionWidth * 3 - lineDivisionWidth, 0, subsectionWidth * 3, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                setCx(event.getX());
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() < subsectionWidth * 2 - subsectionWidth / 2) {
                    setCx(subsectionWidth - lineDivisionWidth / 2);
                    if (listener != null) listener.dataChangeListener(0);
                } else if (event.getX() >= subsectionWidth * 2 - subsectionWidth / 2 && event.getX() < subsectionWidth * 3 - subsectionWidth / 2) {
                    setCx(subsectionWidth * 2 - lineDivisionWidth / 2);
                    if (listener != null) listener.dataChangeListener(1);
                } else if (event.getX() >= subsectionWidth * 3 - subsectionWidth / 2) {
                    setCx(subsectionWidth * 3 - lineDivisionWidth / 2);
                    if (listener != null) listener.dataChangeListener(2);
                }
                break;
        }
        return true;
    }

    private void setCx(float cx) {
        this.cx = cx;
        invalidate();
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    public void setKilometreList(List<String> kilometreList) {
        this.kilometreList = kilometreList;
        invalidate();
    }

    public interface OnDataChangeListener {
        void dataChangeListener(int position);
    }
}
