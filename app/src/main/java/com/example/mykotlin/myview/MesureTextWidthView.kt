package com.example.mykotlin.myview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class MesureTextWidthView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var paint: Paint

    init {
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.textSize = 40f
        paint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
//        var progrressText ="100000/900000"
//        var rect = Rect()
//        // TextBounds 控制偏移量
//        paint.getTextBounds(progrressText, 0, progrressText.length,rect)
//        var offset = (rect.top + rect.bottom) / 2
//        canvas.drawText(progrressText, (width / 2).toFloat(), (height / 2) - offset.toFloat(), paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.WHITE
        var path = Path()
        // 二次贝塞尔曲线
        path.moveTo(0f, height.toFloat())
        path.quadTo(
            width.toFloat() / 2,
            height.toFloat() - height.toFloat() / 5,
            width.toFloat(),
            height.toFloat()
        )
        canvas.drawPath(path, paint);
        // fontMetrics 控制偏移量
//         paint.getFontMetrics(fontMetrics)
        // var offset = (fontMetrics.ascent + fontMetrics.descent) / 2 canvas.drawText("abab", (width / 2).toFloat(), (height / 2) - offset, paint)
    }

    private fun measureTextWidth(text: String) {
        var textWidth = paint.measureText(text)
        var annularWidth = paint.measureText(text)
        // Log.e("------","measureText:"+textWidth+",synopsisText:"+annularWidth)

        // var layout = StaticLayout.Builder.obtain(text,0,text.length, TextPaint(),0).build()
//        Log.e("------","StaticLayout:"+ layout.width)

        var rect = Rect()
//        paint.getTextBounds(text,0,text.length,rect)
//        if((text)>rect.width()){
//            //    Log.e("------","没有超出:getTextBounds:"+rect.width())
//        }else{
//            // Log.e("------","超出了")
//        }
    }
}