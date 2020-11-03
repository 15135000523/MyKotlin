package com.example.mykotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import com.example.mykotlin.R;
import com.example.mykotlin.utils.LinearGradientUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectorView extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    private Paint paint;

    private int lineStartSelectorColor;
    private int lineEndSelectorColor;
    //分割线颜色
    private int lineDivisionColor;
    //色值集合
    private ArrayList<Integer> colorSectionList;
    //色值区间个数
    private int colorNumberOfIntervals;
    //起始色值和结束色值的色差
    private int chromaticAberration;

    //字体颜色
    private int selectorTextColor;

    //分割线宽度
    private int lineDivisionWidth;
    //分段宽度
    private int subsectionWidth;
    //分段高度
    private int subsectionHeight;
    //分段数
    private int subsectionNum;

    //小球移动的X轴
    private float cx;
    //数据集
    private List<String> kilometreList;

    private OnDataChangeListener listener;

    private LinearGradientUtil linearGradientUtil;

    public SelectorView(Context context) {
        this(context, null);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectorView);
        //初始化属性值
        initXmlType(array);
        //初始化中间色值
        initlineColorSectionList();
        initPaint();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void initXmlType(TypedArray array) {
        lineStartSelectorColor = array.getColor(R.styleable.SelectorView_lineStartSelectorColor, 0xFF7BF6D2);
        lineEndSelectorColor = array.getColor(R.styleable.SelectorView_lineEndSelectorColor, 0xFF0DAB7D);
        lineDivisionColor = array.getColor(R.styleable.SelectorView_lineDivisionColor, 0xffffffff);
        selectorTextColor = array.getColor(R.styleable.SelectorView_selectorTextColor, 0xff000000);
        lineDivisionWidth = (int) array.getDimension(R.styleable.SelectorView_lineDivisionWidth, (float) (subsectionWidth * 0.06));
        subsectionNum = array.getInteger(R.styleable.SelectorView_subsectionNum, 3);
        array.recycle();

    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAlpha(255);
    }

    private void initlineColorSectionList() {
        linearGradientUtil = new LinearGradientUtil(lineStartSelectorColor, lineEndSelectorColor);
        colorSectionList = new ArrayList<>();
        chromaticAberration = lineEndSelectorColor - lineStartSelectorColor;
        colorNumberOfIntervals = subsectionNum - 2;
        for (int i = 0; i < subsectionNum; i++) {
            if (i == 0) {
                colorSectionList.add(lineStartSelectorColor);
            } else if (i == subsectionNum - 1) {
                colorSectionList.add(lineEndSelectorColor);
            } else {
                colorSectionList.add(linearGradientUtil.getColor((float) (i / (colorNumberOfIntervals + 1.0))));
            }
        }
        linearGradientUtil = null;

    }

    /**
     * 初始化分段宽高，分割宽
     */
    private void initSubsectionWidthAndHeight() {
        subsectionHeight = getHeight() / 3;
        subsectionWidth = getWidth() / subsectionNum;
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
        paint.setColor(selectorTextColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(subsectionHeight / 2);
        if (kilometreList != null) {
            for (int i = 0; i < kilometreList.size(); i++) {
                canvas.drawText(kilometreList.get(i), subsectionWidth / 2 + subsectionWidth * i, subsectionHeight * 2 - subsectionHeight / 2, paint);
            }
        }
    }

    private void drawLine(Canvas canvas) {
        paint.setStrokeWidth(subsectionHeight);
        for (int i = 0; i < colorSectionList.size(); i++) {
            paint.setColor(colorSectionList.get(i));
            canvas.drawLine(subsectionWidth * i, 0, subsectionWidth * (i + 1) - lineDivisionWidth, 0, paint);
            paint.setColor(lineDivisionColor);
            canvas.drawLine(subsectionWidth * (i + 1) - lineDivisionWidth, 0, subsectionWidth * (i + 1), 0, paint);
        }
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
                for (int i = 0; i < colorSectionList.size(); i++) {
                    if (subsectionWidth * i < event.getX() && event.getX() <= subsectionWidth * (i + 1)) {
                        if(i<colorSectionList.size()-1){
                            setCx(subsectionWidth * (i + 1) - lineDivisionWidth / 2);
                        }else {
                            setCx(subsectionWidth * (i + 1) - lineDivisionWidth);
                        }
                        if (listener != null) listener.dataChangeListener(i);
                        break;
                    }
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

    @Override
    public void onGlobalLayout() {
        initSubsectionWidthAndHeight();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    public interface OnDataChangeListener {
        /**
         * 返回选中节点的position
         *
         * @param position
         */
        void dataChangeListener(int position);
    }
}
