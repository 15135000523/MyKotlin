package com.example.mykotlin.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class CircleImageView(context: Context?, attrs: AttributeSet) : ImageView(context, attrs) {

    /**
     *android.graphics.PorterDuff.Mode.SRC:只绘制源图像
     *android.graphics.PorterDuff.Mode.DST:只绘制目标图像
     *android.graphics.PorterDuff.Mode.DST_OVER:在源图像的顶部绘制目标图像
     *android.graphics.PorterDuff.Mode.DST_IN:只在源图像和目标图像相交的地方绘制目标图像
     *android.graphics.PorterDuff.Mode.DST_OUT:只在源图像和目标图像不相交的地方绘制目标图像
     *android.graphics.PorterDuff.Mode.DST_ATOP:在源图像和目标图像相交的地方绘制目标图像，在不相交的地方绘制源图像
     *android.graphics.PorterDuff.Mode.SRC_OVER:在目标图像的顶部绘制源图像
     *android.graphics.PorterDuff.Mode.SRC_IN:只在源图像和目标图像相交的地方绘制源图像
     *android.graphics.PorterDuff.Mode.SRC_OUT:只在源图像和目标图像不相交的地方绘制源图像
     *android.graphics.PorterDuff.Mode.SRC_ATOP:在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像
     *android.graphics.PorterDuff.Mode.XOR:在源图像和目标图像重叠之外的任何地方绘制他们，而在不重叠的地方不绘制任何内容
     *android.graphics.PorterDuff.Mode.LIGHTEN:获得每个位置上两幅图像中最亮的像素并显示
     *android.graphics.PorterDuff.Mode.DARKEN:获得每个位置上两幅图像中最暗的像素并显示
     *android.graphics.PorterDuff.Mode.MULTIPLY:将每个位置的两个像素相乘，除以255，然后使用该值创建一个新的像素进行显示。结果颜色=顶部颜色*底部颜色/255
     *android.graphics.PorterDuff.Mode.SCREEN:反转每个颜色，执行相同的操作（将他们相乘并除以255），然后再次反转。结果颜色=255-(((255-顶部颜色)*(255-底部颜色))/255)
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = Color.RED
        paint.isAntiAlias = true
        var xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        paint.setXfermode(xfermode)
        canvas?.drawCircle(width / 2f, height / 2f, 100f, paint)
    }
}