package com.example.mykotlin.ui.youku.main.fragment.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.mykotlin.ui.youku.main.fragment.banner.IndicatorEnum.*;

/**
 * banner 指示器
 */
public class IndicatorView extends View {

    private Paint mPiant;
    private int indicatorColor = 0xff666666;
    private int selectColor = 0xffFF0000;
    private int textColor = 0xff666666;
    private int textSize = 30;
    private int titleSize = 40;
    private float indicatorRadius = 10;
    private float indicatorInterval = 20;
    private int mBackground = 0x11ffffff;

    private List<String> titles;

    //指示器类型，默认底部居中指示器模式
    private IndicatorEnum indicatorType = BOTTOM_RIGHT_SPOT_TEXT;
    //当前显示内容的下标
    private int position = 0;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPiant = new Paint();
        mPiant.setAntiAlias(true);
        mPiant.setStyle(Paint.Style.FILL_AND_STROKE);
        titles = new ArrayList<>();
        titles.add("标题1");
        titles.add("标题2");
        titles.add("标题3");
        titles.add("标题4");
        titles.add("标题5");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(mBackground);
        String str = (1 + position) + "/" + (titles == null ? "0" : String.valueOf(titles.size()));
        switch (indicatorType) {
            case BOTTOM_CENTER_SPOT:
                calculationIndictorPlace(canvas);
                break;
            case BOTTOM_RIGHT_SPOT:
                calculationIndictorPlace(canvas);
                break;
            case BOTTOM_CENTER_PROPORTION:
                drawText(canvas, getWidth() / 2, getHeight() / 2, Paint.Align.CENTER, str, textSize);
                break;
            case BOTTOM_RIGHT_PROPORTION:
                drawText(canvas, getWidth(),  getHeight() / 2 + textSize / 2, Paint.Align.RIGHT, str, textSize);
                break;
            case BOTTOM_RIGHT_SPOT_TEXT:
                calculationIndictorPlace(canvas);
                drawText(canvas, 100, getHeight() / 2 + titleSize / 2, Paint.Align.LEFT, titles.get(position), titleSize);
                break;
            case BOTTOM_RIGHT_PROPORTION_TEXT:
                //画数字指示器
                drawText(canvas, getWidth() - 100, getHeight() / 2 + textSize / 2, Paint.Align.RIGHT, str, textSize);
                //画标题
                drawText(canvas, 100, getHeight() / 2 + titleSize / 2, Paint.Align.LEFT, titles.get(position), titleSize);
                break;
        }
    }

    /**
     * 计算指示器每个圆点的位置
     */
    private void calculationIndictorPlace(Canvas canvas) {
        //指示器总长度
        float allIndictorWidth = titles.size() * indicatorRadius * 2 + (titles.size() - 1) * indicatorInterval;
        float startX = 0;
        float startY = getHeight() / 2;
        switch (indicatorType) {
            case BOTTOM_CENTER_SPOT:
                startX = getWidth() / 2 - allIndictorWidth / 2;
                break;
            case BOTTOM_RIGHT_SPOT:
                startX = getWidth()-allIndictorWidth;
                break;
            case BOTTOM_RIGHT_SPOT_TEXT:
                startX = getWidth()-allIndictorWidth;
                break;
        }
        loopDraw(canvas, startX, startY);
    }

    /**
     * @param canvas 画布
     * @param startX 起点X
     * @param startY 起点Y
     */
    private void loopDraw(Canvas canvas, float startX, float startY) {
        for (int i = 0; i < titles.size(); i++) {
            if (position == i)
                drawIndictor(canvas, startX + indicatorRadius * i + indicatorInterval * i, startY, selectColor);
            else
                drawIndictor(canvas, startX + indicatorRadius * i + indicatorInterval * i, startY, indicatorColor);
        }
    }


    /**
     * 画圆点
     *
     * @param canvas 画布
     * @param x      圆点的X
     * @param y      圆点的Y
     * @param color  圆点的颜色
     */
    private void drawIndictor(Canvas canvas, float x, float y, int color) {
        mPiant.setColor(color);
        canvas.drawCircle(x, y, indicatorRadius, mPiant);
    }

    /**
     * 画数字模式的数字
     */
    private void drawText(Canvas canvas, int x, int y, Paint.Align align, String str, int tSize) {
        mPiant.setTextSize(tSize);
        mPiant.setTextAlign(align);
        mPiant.setColor(textColor);
        canvas.drawText(str, x, y, mPiant);
    }

    public IndicatorEnum getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(IndicatorEnum indicatorType) {
        this.indicatorType = indicatorType;
        invalidate();
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        invalidate();
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public float getIndicatorRadius() {
        return indicatorRadius;
    }

    public void setIndicatorRadius(float indicatorRadius) {
        this.indicatorRadius = indicatorRadius;
    }

    public float getIndicatorInterval() {
        return indicatorInterval;
    }

    public void setIndicatorInterval(float indicatorInterval) {
        this.indicatorInterval = indicatorInterval;
    }

    public int getmBackground() {
        return mBackground;
    }

    public void setmBackground(int mBackground) {
        this.mBackground = mBackground;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
