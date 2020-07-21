package com.example.mykotlin.myview

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.example.mykotlin.R



class CanvasText(context:Context) : View(context){
    var paint:Paint = Paint()
    var bitmap : Bitmap
    var degree = 0
    var animator: ObjectAnimator = ObjectAnimator.ofInt(this, "degree", 0, 180);

    init{
        bitmap = BitmapFactory.decodeResource(resources,R.mipmap.liu);
        animator.setDuration(2500);
        animator.setInterpolator( LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener {
            this.degree = it.getAnimatedValue("degree") as Int
            invalidate()
        }
    }

    constructor( context:Context,attrs: AttributeSet) : this(context) {
    }
    constructor( context:Context,attrs: AttributeSet,defStyleAttr:Int) : this(context,attrs) {
    }
    constructor( context:Context,attrs: AttributeSet,defStyleAttr:Int, defStyleRes:Int) : this(context,attrs,defStyleAttr) {
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = ContextCompat.getColor(context,R.color.Orange)
        canvas?.drawRect(Rect(0,0,width,height),paint)

//        clipRect(canvas)
        //canvas几何变换是反向的，先写后执行
//        canvasGeometricTransformation(canvas)
//        matrixGeometricTransformation(canvas)
//        cameraGeometricTransformation(canvas)
//        translatetoCameraCenter(canvas);
//        someBitmapTra(canvas)

        var camera = Camera();
        camera.save()
        canvas?.save()
        camera.rotateX(20f)
        canvas?.translate(400f,500f)
        camera.applyToCanvas(canvas)
        canvas?.translate(-400f,-500f)
        camera.restore()
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources,R.mipmap.liu),200f,100f,paint)
        canvas?.restore()

    }

    /**
     *将camera的中心点位移到view中间
     */
    private fun translatetoCameraCenter(canvas: Canvas?){
        var camera = Camera();
        canvas?.save()
        camera.rotateX(20f);
        canvas?.translate(((right-left)/2).toFloat(),((bottom-top)/2+(bitmap.height/2)).toFloat())
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas?.translate(-((right-left)/2).toFloat(),-((bottom-top)/2+(bitmap.height/2)).toFloat())
        camera.restore()
        canvas?.drawBitmap(bitmap,((right-left- bitmap.width)/2).toFloat(),((bottom-top- bitmap.height)/2).toFloat(),paint)
        canvas?.restore()
    }

    /**
     * 先画上半部分
     * 再画下半部分，并且以中心位置旋转下半部分
     */
    private fun someBitmapTra(canvas: Canvas?){
        var bitmapWidth = bitmap.width
        var bitmapHeight = bitmap.height
        var centerX = width/2;
        var centerY = height/2
        var x = centerX-bitmapWidth/2
        var y = centerY-bitmapHeight/2
        canvas?.save()
        canvas?.clipRect(Rect(0,0,right,centerY))
        canvas?.drawBitmap(bitmap,x.toFloat(),y.toFloat(),paint)
        canvas?.restore()

        var camera = Camera();

        // 第二遍绘制：下半部分
        canvas?.save();

        if (degree < 90) {
            canvas?.clipRect(0, centerY, getWidth(), getHeight());
        } else {
            canvas?.clipRect(0, 0, getWidth(), centerY);
        }
        camera.save();
        camera.rotateX(degree.toFloat());
        canvas?.translate(centerX.toFloat(), centerY.toFloat());
        camera.applyToCanvas(canvas);
        canvas?.translate(-centerX.toFloat(), -centerY.toFloat());
        camera.restore();

        canvas?.drawBitmap(bitmap, x.toFloat(), y.toFloat(), paint);
        canvas?.restore();
    }

    private fun cameraGeometricTransformation(canvas: Canvas?){
        var camera = Camera();
        camera.save()
        canvas?.save()
        camera.rotateX(-30f)
         camera.rotateY(30f)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources,R.mipmap.liu),0f,0f,paint)
        canvas?.restore()
    }

    /**
     * 矩阵几何变换
     */
    private fun matrixGeometricTransformation(canvas: Canvas?){
        var matrix = Matrix();
        matrix.reset()
        matrix.postTranslate(300f,0f)
        matrix.postRotate(45f)
        matrix.postScale(0.5f,0.5f)
        matrix.postSkew(0.3f,0.3f)
        canvas?.save()
        canvas?.concat(matrix)
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources,R.mipmap.liu),0f,0f,paint)
        canvas?.restore()
    }

    /**
     * canvas几何变换
     */
    private fun canvasGeometricTransformation(canvas: Canvas?){
        canvas?.save()
        canvas?.skew(0.0f,0.5f)//错切
        canvas?.scale(0.7f,0.7f)//缩放
        canvas?.rotate(45f)//旋转
        canvas?.translate(400f,0f)//位移
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources,R.mipmap.liu),0f,0f,paint)
        canvas?.restore()
    }

    /**
     * 范围裁切，可以指定路径，可以指定矩阵
     */
    private fun clipRect(canvas: Canvas?){
        canvas?.save()
        canvas?.clipRect(100, 0, 700, 600);
        canvas?.drawBitmap(bitmap, 100f,100f,paint)
        canvas?.restore()

        var path1 = Path()
        path1.addCircle(300f,200f,200f,Path.Direction.CW)
        canvas?.save()
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap,0f,0f,paint)
        canvas?.restore()
    }
}