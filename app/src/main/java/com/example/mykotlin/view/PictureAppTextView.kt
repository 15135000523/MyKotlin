package com.example.mykotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.mykotlin.R

class PictureAppTextView(context: Context,attrs: AttributeSet):AppCompatTextView(context,attrs){
    init{

    }
    constructor(context:Context, attrs: AttributeSet, defStyleAttr:Int) : this(context,attrs) {
    }
    constructor(context:Context, attrs: AttributeSet, defStyleAttr:Int, defStyleRes:Int) : this(context,attrs,defStyleAttr) {
    }


    override fun onDraw(canvas: Canvas?) {
        var paint = Paint();
        paint.color = ContextCompat.getColor(context, R.color.Orange)
        paint.textSize = 20f
        canvas?.drawRect(RectF(0f,0f,(width-paddingLeft-paddingRight).toFloat(),100f),paint)
        super.onDraw(canvas)
    }
}