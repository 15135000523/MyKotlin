package com.example.mykotlin.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.mykotlin.R

class PictureAppImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.Orange)
        canvas?.drawCircle(100f, 100f, 30f, paint)
        canvas?.drawCircle(150f, 200f, 15f, paint)
    }
}