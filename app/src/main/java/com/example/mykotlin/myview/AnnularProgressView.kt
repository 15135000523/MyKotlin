package com.example.mykotlin.myview

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.mykotlin.R
import com.example.mykotlin.utils.ViewUtils

/**
 * 环形进度条
 */
class AnnularProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var paint:Paint

    var progressColor : Int //进度条颜色
    var annularColor :Int //环形颜色
    var annularWidth :Float //环形宽度
    var textColor: Int //字体颜色
    var synopsisText:String //介绍文字
    var progressTextSize:Float //进度字体大小
    var synopsisTextSize:Float //介绍字体大小
    var allProgress :Int //总进度
    var finishProgress:Int //完成进度
    var synopsisTextMarginTop = 0f //进度文字和介绍文字的上下间距

    var paddingMax : Float //padding中的最大值，防止环形变形

    var degree = 0f //当前动画的执行进度
    var finishDegree :Float //动画执行的终止进度
    var animator: ObjectAnimator
    init{
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        var typeArray = context.obtainStyledAttributes(attrs, R.styleable.AnnularProgressView)

        annularColor = typeArray.getColor(R.styleable.AnnularProgressView_annularColor,Color.LTGRAY)
        progressColor = typeArray.getColor(R.styleable.AnnularProgressView_progressColor,Color.YELLOW)
        textColor =typeArray.getColor(R.styleable.AnnularProgressView_textColor,Color.YELLOW)
        synopsisText = typeArray.getString(R.styleable.AnnularProgressView_synopsisText)?:"空"
        progressTextSize = typeArray.getDimension(R.styleable.AnnularProgressView_progressTextSize,20.0f)
        synopsisTextSize = typeArray.getDimension(R.styleable.AnnularProgressView_synopsisTextSize,12.0f)
        annularWidth = typeArray.getDimension(R.styleable.AnnularProgressView_annularWidth,12.0f)
        allProgress = typeArray.getInt(R.styleable.AnnularProgressView_allProgress,100)
        finishProgress = typeArray.getInt(R.styleable.AnnularProgressView_finishProgress,10)

        paddingMax = measurePadding().toFloat()

        finishDegree =360f*finishProgress/allProgress
        animator = ObjectAnimator.ofFloat(this, "degree", 0f,finishDegree)
        animator.setDuration(2500)
        animator.setInterpolator( LinearInterpolator())
        animator.addUpdateListener {
            this.degree = it.getAnimatedValue("degree") as Float
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    override fun onDraw(canvas: Canvas) {
        drawAnnular(canvas)
        drawProgress(canvas)
        drawText(canvas)
    }

    /**
     * 找出最大的padding
     */
    private fun measurePadding():Int{
        var paddingArr = arrayOf(paddingLeft, paddingRight, paddingBottom,paddingTop)
        return paddingArr.max()!!
    }

    /**
     * 画环形
     */
    @SuppressLint("NewApi")
    private fun drawAnnular(canvas: Canvas){
        paint.strokeWidth = ViewUtils.dp2px(annularWidth)
        paint.style = Paint.Style.STROKE
        paint.color = annularColor

        canvas.drawArc(paint.strokeWidth/2+paddingMax,paint.strokeWidth/2+paddingMax,
            right.toFloat()-paint.strokeWidth/2-paddingMax,bottom.toFloat()- paint.strokeWidth/2-paddingMax,0f,360f,true,paint)

    }

    /**
     * 画进度
     */
    @SuppressLint("NewApi")
    private fun drawProgress(canvas: Canvas){
        paint.color = progressColor
        var path = Path()
        if(degree==360.0f){
            canvas.drawArc(paint.strokeWidth/2+paddingMax,paint.strokeWidth/2+paddingMax,
                right.toFloat()-paint.strokeWidth/2-paddingMax,bottom.toFloat()- paint.strokeWidth/2-paddingMax,0f,360f,true,paint)

        }else{
            path.arcTo(RectF( paint.strokeWidth/2+paddingMax,paint.strokeWidth/2+paddingMax,
                right.toFloat()-paint.strokeWidth/2-paddingMax,bottom.toFloat()- paint.strokeWidth/2-paddingMax),0f,degree,false)
            canvas.drawPath(path,paint)
        }

    }

    /**
     * 画文字
     */
    private fun drawText(canvas: Canvas){
        paint.color=textColor
        paint.textSize=progressTextSize
        paint.strokeWidth =3f
        paint.style = Paint.Style.FILL_AND_STROKE
        var progrressText ="$finishProgress/$allProgress"
        paint.textAlign =Paint.Align.CENTER
        if(synopsisText.equals("空")){
            canvas.drawText(progrressText,(right/2).toFloat(),(bottom/2).toFloat(),paint)
        }else{
            canvas.drawText(progrressText,(right/2).toFloat(),(bottom-progressTextSize-synopsisTextSize- synopsisTextMarginTop)/2+progressTextSize/2,paint)

            paint.textSize=synopsisTextSize
            var synopsisY =(bottom-progressTextSize-synopsisTextSize- synopsisTextMarginTop)/2+progressTextSize+synopsisTextSize//文字开始的y
            canvas.drawText(synopsisText,(right/2).toFloat(),synopsisY,paint)
        }
//        measuretextWidth(progrressText)
    }

    /**
     * 测量字符是否超出容器大小
     */
    private fun measuretextWidth(text:String):Boolean{
        var rect = Rect()
        paint.getTextBounds(text,0,text.length,rect)
        if((right-left-paddingMax-paddingMax-annularWidth)>rect.width()){
            Log.e("------","没有超出")
            return false
        }else{k
            Log.e("------","超出了")
        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(widthMeasureSpec>heightMeasureSpec){
            super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        }else{
            super.onMeasure(heightMeasureSpec, heightMeasureSpec)

        }
    }
}