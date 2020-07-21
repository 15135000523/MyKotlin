package com.example.mykotlin.myview

import android.animation.TypeEvaluator
import android.graphics.RectF
import android.util.Log

class PointEvaluator :TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        var sweepAngle = fraction*300
        if(sweepAngle<=90){
            var nowPaint= Point(180f,90f,RectF(90f,90f,500f,500f))
            return Point(180f,sweepAngle,RectF(90f,90f,500f,500f));
        }
        Log.e("-----","自定义的："+sweepAngle+",fraction基数:"+fraction)
        return Point(180f,90f,RectF(90f,90f,500f,500f));
    }
}