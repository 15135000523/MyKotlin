package com.example.mykotlin.myview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.mykotlin.R

class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var paint: Paint

    private var movex = 0.0f
    private var movey = 1.0f
    private var radus = 1.0f
    var path = Path()
    var index = 0
    private var animator: ValueAnimator = ValueAnimator()
    var startPoint = Point(180f, 0f, RectF(90f, 90f, 490f, 490f))
    var endPoint = Point(180f, 90f, RectF(90f, 90f, 490f, 490f))

    init {
        animator = ValueAnimator.ofObject(PointEvaluator(), startPoint, endPoint)
        animator.duration = 3000
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener() {
            val point = (it.getAnimatedValue() as Point)
            if (point.sweepAngle < endPoint.sweepAngle) {
                radus += 2f
                path.arcTo(startPoint.writeFectf, startPoint.startAngle, point.sweepAngle, true)
            } else if (movex <= (startPoint.writeFectf.right - startPoint.writeFectf.left) / 2) {
                path.arcTo(startPoint.writeFectf, startPoint.startAngle, endPoint.sweepAngle, true)
                path.moveTo(
                    (startPoint.writeFectf.right - startPoint.writeFectf.left) / 2 + startPoint.writeFectf.left,
                    startPoint.writeFectf.top
                )
                movex += 4
                path.lineTo(
                    (startPoint.writeFectf.right - startPoint.writeFectf.left) / 2 + startPoint.writeFectf.left,
                    startPoint.writeFectf.top + movex
                )
            } else if (movey <= (startPoint.writeFectf.bottom - startPoint.writeFectf.top) / 2) {
                movey += 4
                path.lineTo(
                    ((startPoint.writeFectf.right - startPoint.writeFectf.left) / 2 + startPoint.writeFectf.left) - movey,
                    startPoint.writeFectf.top + movex
                )
            }else if(movey>=90){
                animator.end()
                animator.removeAllUpdateListeners()
            }
            index++
            Log.e("-------","动画执行次数:"+index)
            invalidate()
        })

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.e("-------","onSizeChanged:"+index)
        animator.start()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint = Paint()

        paintRect(canvas)
        paintArc(canvas)

        paintColor(R.color.Green)
        paint.setStyle(Paint.Style.STROKE);
        paint.strokeWidth = 3.0f
        paint.textSize = 30f

        //在矩阵区域内画个弧线 boolean表示是否连线
//        path.moveTo((startPoint.writeFectf.right-startPoint.writeFectf.left)/2+startPoint.writeFectf.left,
//            startPoint.writeFectf.top )
//        path.lineTo((startPoint.writeFectf.right-startPoint.writeFectf.left)/2+startPoint.writeFectf.left,
//            (startPoint.writeFectf.bottom-startPoint.writeFectf.top)/2+startPoint.writeFectf.top)
//        path.lineTo((startPoint.writeFectf.left),
//            (startPoint.writeFectf.bottom-startPoint.writeFectf.top)/2+startPoint.writeFectf.top)
        canvas?.drawPath(path, paint)
        canvas?.drawText("西北", 200f, 200f, paint)


//        path.arcTo(RectF(110f,90f,510f,490f),270f,90f,true)
//        path.moveTo(310f,90f)
//        path.lineTo(310f,290f)
//        path.lineTo(510f,290f)
//        canvas?.drawText("东北",350f,200f ,paint)
////        canvas?.drawPath(path ,paint)
//
//        path.arcTo(RectF(110f,110f,510f,510f),0f,90f,true)
//        path.moveTo(510f,310f)
//        path.lineTo(310f,310f)
//        path.lineTo(310f,510f)
//        canvas?.drawText("东南",350f,400f ,paint)
////        canvas?.drawPath(path ,paint)
//        pathMesure.setPath(path,false)
//        var a = Point();
//
//        path.arcTo(RectF(90f,110f,490f,510f),90f,90f,true)
//        path.moveTo(290f,510f)
//        path.lineTo(290f,310f)
//        path.lineTo(90f,310f)
//        canvas?.drawText("西南",200f,400f ,paint)
//        canvas?.drawPath(path ,paint)

        val bitmap: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.mipmap.img
        )
        canvas?.drawBitmap(
            bitmap, Rect(110, 510, 410, 810),
            RectF(110f, 510f, 410f, 810f),
            paint
        )
        paint.alpha = 255
        canvas?.drawArc(RectF(110f, 510f, 410f, 810f), 0f, 360f, true, paint)
    }

    /**
     * 画矩阵
     */
    private fun paintRect(canvas: Canvas?) {
        paintColor(R.color.Green)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
        canvas?.drawRect(
            0.0f + paddingLeft,
            0.0f + paddingTop,
            width.toFloat() - paddingRight,
            height.toFloat() - paddingBottom, paint
        )

        paintColor(R.color.red)
        canvas?.drawRoundRect(
            RectF(20f, 30f, (width - 20).toFloat(), (height - 30).toFloat()),
            180.0f,
            180.0f,
            paint
        )
    }

    /**
     * 画扇形
     */
    private fun paintArc(canvas: Canvas?) {
        paintColor(R.color.Orange)
        //中心点为坐标点，以X正轴开始，
        canvas?.drawArc(RectF(110f, 110f, 510f, 510f), 0f, 90f, true, paint)

//        paintColor(R.color.blue)
        canvas?.drawArc(RectF(90f, 110f, 490f, 510f), 90f, 90f, true, paint)

//        paintColor(R.color.black)
        canvas?.drawArc(RectF(90f, 90f, 500f, 500f), 180f, 90f, true, paint)

        //画扇形
//        paintColor(R.color.Yellow)
        canvas?.drawArc(RectF(110f, 90f, 510f, 490f), 270f, 90f, true, paint)
        //画圆
        //canvas?.drawCircle(100f,100f,50f,paint)
    }

    /**
     * paint.setAntiAlias(true);//抗锯齿功能
     *paint.setColor(Color.RED);  //设置画笔颜色
     *paint.setStyle(Style.FILL);//STROKE：描边2.FILL_AND_STROKE：描边并填充3.FILL：填充
     *paint.setStrokeWidth(10);//设置画笔宽度 ，单位px
     *paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
     */
    private fun paintColor(color: Int) {
        paint.color = ContextCompat.getColor(context, color)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
}