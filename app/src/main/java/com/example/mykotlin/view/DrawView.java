package com.example.mykotlin.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.core.view.GestureDetectorCompat;

import java.util.Date;

public class DrawView extends View implements ViewTreeObserver.OnGlobalLayoutListener {
    private float currentX = 40;
    private float currentY = 50;
    private float lastTimeX, lastTimeY;

    private float downX, downY;
    private long downTime, upTime;
    private float upX, upY;
    //计算的速度
    private float acceleration;
    //递减因子
    private int decline = 100;

    private int width, height;

    private ValueAnimator animator;

    // 定义创建画笔
    Paint p = new Paint();

    private GestureDetectorCompat detector;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet set) {
        super(context, set);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        detector = new GestureDetectorCompat(context, new MyGestureDetector());
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.RED); // 画笔颜色
        canvas.drawCircle(currentX, currentY, 15, p);

        canvas.drawCircle(25, 25, 25, p);
        canvas.drawCircle(width-25, 25, 25, p);
        canvas.drawCircle(25, height/2.0f, 25, p);
        canvas.drawCircle(width-25, height/2.0f, 25, p);
        canvas.drawCircle(25, height-25, 25, p);
        canvas.drawCircle(width-25, height-25, 25, p);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (animator != null && animator.isStarted()) {
            animator.end();
        }
        detector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = new Date().getTime();
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                lastTimeX = currentX;
                lastTimeY = currentY;
                currentX = event.getX();
                currentY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTime = new Date().getTime();
                upX = event.getX();
                upY = event.getY();
                initAcceleration2StartAnimation();
                break;
        }
        return true;
    }

    /**
     * 初始化加速度并开始动画
     */
    private void initAcceleration2StartAnimation() {
        //秒时间差
        float timeDifference = (upTime - downTime) / 1000f;
        acceleration = (float) Math.sqrt(Math.pow(Math.abs(upX - downX), 2) + Math.pow(Math.abs(upY - downY), 2)) / timeDifference;
        Log.e("drawview", "acceleration  :" + acceleration);
        initAnimator();

    }

    private void initAnimator() {
        animator = ValueAnimator.ofFloat(acceleration, 1);
        animator.setDuration((long) ((acceleration / decline / 16) * 1000));
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            if (Math.abs(downX - upX) > 100 && Math.abs(downY - upY) > 100) {
                if (currentX - lastTimeX > 0) {
                    lastTimeX = currentX;
                    currentX = currentX + value / decline;
                } else {
                    lastTimeX = currentX;
                    currentX = currentX - value / decline;
                }
                if (currentY - lastTimeY > 0) {
                    lastTimeY = currentY;
                    currentY = currentY + value / decline;
                } else {
                    lastTimeY = currentY;
                    currentY = currentY - value / decline;
                }
            } else if (Math.abs(downX - upX) > 100) {
                if (currentX - lastTimeX > 0) {
                    lastTimeX = currentX;
                    currentX = currentX + value / decline;
                } else {
                    lastTimeX = currentX;
                    currentX = currentX - value / decline;
                }

            } else if (Math.abs(downY - upY) > 100) {
                if (currentY - lastTimeY > 0) {
                    lastTimeY = currentY;
                    currentY = currentY + value / decline;
                } else {
                    lastTimeY = currentY;
                    currentY = currentY - value / decline;
                }
            }

            if (currentX >= width) {
                currentX = width - Math.abs(width - currentX);
            }
            if (currentY >= height) {
                currentY = height - Math.abs(height - currentY);
            }
            if (currentX <= 15) {
                currentX = Math.abs(currentX);
            }
            if (currentY <= 15) {
                currentY = Math.abs(currentY);
            }

            invalidate();
        });
        animator.start();
    }


    @Override
    public void onGlobalLayout() {
        width = getWidth();
        height = getHeight();
        currentX = width/2;
        currentY = height/6*5;
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public MyGestureDetector() {
            super();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("drawview", "velocityX  :" + velocityX + " ---velocityY:" + velocityY);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }
}