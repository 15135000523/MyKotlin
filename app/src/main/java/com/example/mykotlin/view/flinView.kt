package com.example.mykotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.DecelerateInterpolator
import android.widget.OverScroller
import kotlin.math.absoluteValue

class flinView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var lastY = 0F
    private var offsetY = 0F
    private val velocityTracker = VelocityTracker.obtain()
    private val overScroller = OverScroller(context, DecelerateInterpolator())
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 100F
        paint.color = Color.BLACK
        canvas.translate(0F, -offsetY)
        canvas.drawText("FlinView", width / 2F, height / 2F, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastY = event.y
                return true
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000)
                if (velocityTracker.yVelocity.absoluteValue > ViewConfiguration.get(context).scaledMinimumFlingVelocity) {
//                    if(velocityTracker.yVelocity>0){
//                        overScroller.fling(
//                                0,
//                        offsetY.toInt(),
//                        0,
//                        velocityTracker.yVelocity.toInt(),
//                        0,
//                        0,
//                        400,
//                        0,
//                        0,
//                        400
//                        )
//                    }else{
//                        overScroller.fling(
//                            0,
//                            offsetY.toInt(),
//                            0,
//                            velocityTracker.yVelocity.toInt(),
//                            0,
//                            0,
//                            0,
//                            -400,
//                            0,
//                            400
//                        )
//                    }

                    overScroller.fling(
                        0,
                        offsetY.toInt(),
                        0,
                        velocityTracker.yVelocity.toInt(),
                        0,
                        0,
                        400,
                        0,
                        0,
                        400
                    )

                    postInvalidateOnAnimation()
                }

            }
            MotionEvent.ACTION_MOVE -> {

            }
        }
        return false
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            offsetY = overScroller.currY.toFloat()
            Log.e("flin","overScroller.currY ${overScroller.currY}")
            invalidate()
        }
        super.computeScroll()
    }
}