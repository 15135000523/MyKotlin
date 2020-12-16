package com.example.mykotlin.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class MyClock extends View implements ViewTreeObserver.OnGlobalLayoutListener {
    private Paint mPaint;
    private int clockDialColor;
    private int hourHandColor;
    private int minuteHandColor;
    private int secondHandColor;
    private boolean isShowText = true;
    private Drawable BackgroundDrawable;
    private int width, height, radius;
    private float scaleSize = 30;
    private float scaleLongSize = 40;
    private int textSize = 24;
    //偏移度
    private float minuteDegrees, hourDegrees, secondDegrees;
    private int currentSecond;
    private ValueAnimator animator;

    public MyClock(Context context) {
        this(context, null);
    }

    public MyClock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        calculateDegrees();
        initAnimator();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAlpha(255);
    }

    private void calculateDegrees() {
        minuteDegrees = 0;
        hourDegrees = 0;

        int currentHour = Calendar.getInstance().get(Calendar.HOUR);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        minuteDegrees = currentMinute * 6;
        //计算时针旋转的角度（当前小时数*每小时旋转的角度+当前分针/总分针比例*每小时旋转的角度）
        hourDegrees = currentHour * 30 + currentMinute / 2;
        //获取当前秒数
        currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        //计算秒针旋转的角度（当前秒数/刻度数60°/周期数360°）
        secondDegrees = currentSecond * 6;

    }

    private void initAnimator() {
        animator = ValueAnimator.ofFloat(secondDegrees, 360.0f);
        animator.setDuration((long) ((360.0f - secondDegrees) / 6 * 1000));
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            secondDegrees = (float) animation.getAnimatedValue();
            invalidate();
            if (secondDegrees == 360.0f) {
                secondDegrees = 0.0f;
                minuteDegrees += 6;
                hourDegrees = Calendar.getInstance().get(Calendar.HOUR) * 30 + Calendar.getInstance().get(Calendar.MINUTE) / 2;
                initAnimator();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        canvas.restore();
        drawScaleLine(canvas);
        //小时指针
        drawPointer(canvas, hourDegrees, width / 2, scaleLongSize + textSize + 10, width / 2, height / 2, 6, mPaint);
        //分针指针
        drawPointer(canvas, minuteDegrees, width / 2, scaleLongSize, width / 2, height / 2, 4, mPaint);
        //秒针
        drawPointer(canvas, secondDegrees, width / 2, scaleSize / 2, width / 2, height / 2, 2, mPaint);
    }

    //绘制刻度尺
    private void drawScaleLine(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(textSize);
        canvas.save();
        //循环绘制刻度线
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(width / 2, 0, width / 2, scaleLongSize, mPaint);
                if (isShowText) {
                    canvas.rotate(-6 * (i + 1), width / 2, textSize + scaleLongSize);
                    canvas.drawText(i / 5 == 0 ? 12 + "" : i / 5 + "", width / 2, textSize + scaleLongSize, mPaint);
                    canvas.rotate(6 * (i + 1), width / 2, textSize + scaleLongSize);
                }
            } else {
                canvas.drawLine(width / 2, 0, width / 2, scaleSize, mPaint);
            }
            canvas.rotate(6, width / 2, height / 2);
        }
        canvas.restore();
    }

    /**
     * 绘制指针
     *
     * @param canvas
     * @param degress
     * @param startX
     * @param startY
     * @param stopX
     * @param stopY
     * @param paint
     */
    private void drawPointer(Canvas canvas, float degress, float startX, float startY,
                             float stopX, float stopY, int pointerWidth, Paint paint) {
        paint.setStrokeWidth(pointerWidth);
        canvas.save();
        canvas.rotate(degress, width / 2, height / 2);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.restore();
    }


    public boolean isShowText() {
        return isShowText;
    }

    public void setShowText(boolean showText) {
        isShowText = showText;
    }

    @Override
    public void onGlobalLayout() {
        width = getWidth();
        height = getHeight();
        radius = width > height ? height / 2 : width / 2;
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

}
