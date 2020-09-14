package com.example.mykotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.example.mykotlin.utils.ScreenUtils.*


class WaterMarkSetting {

    companion object {
        fun createWatermarkBitMap(bitmap: Bitmap, waterMark: String, context: Context): Bitmap {
            val newMap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(newMap)
            var paint = Paint()
            paint.isAntiAlias = true
            //在画布 0，0坐标上开始绘制原始图片
            canvas.drawBitmap(bitmap, 0f, 0f, paint)

            //画水印文本
            var textPaint = TextPaint()
            textPaint.setARGB(0xFF, 0xff, 0, 0)
            textPaint.textSize = ViewUtils.dp2px(16f) * bitmap.width / getScreenWidth(context)
            textPaint.textAlign = Paint.Align.LEFT

            var layout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain(waterMark, 0, waterMark.length, textPaint, ViewUtils.dp2px(600f).toInt()).build()
            } else {
                StaticLayout(waterMark, textPaint, ViewUtils.dp2px(600f).toInt(),
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true)
            }
            var textOffsetX = (bitmap.width/20).toFloat()
            var textOffsetY = (bitmap.height/20).toFloat()

            canvas.translate(textOffsetX, textOffsetY)
            layout.draw(canvas)
            canvas.save()
            canvas.restore()

            return newMap
        }

        fun createWatermarkBitMaps(bitmap: Bitmap, waterMark: String, context: Context): Bitmap {
            val newMap = Bitmap.createBitmap(ScreenUtils.getScreenWidth(context), ScreenUtils.getScreenHeight(context)-ScreenUtils.getStatusBarHeight(context), Bitmap.Config.ARGB_8888)
            var canvas = Canvas(newMap)
            var paint = Paint()
            paint.isAntiAlias = true
            //在画布 0，0坐标上开始绘制原始图片
            canvas.drawBitmap(bitmap, 0f, 0f, paint)

            //画水印文本
            var textPaint = TextPaint()
            textPaint.setARGB(0xFF, 0xff, 0, 0)
            textPaint.textSize = ViewUtils.dp2px(16f)
            textPaint.textAlign = Paint.Align.LEFT

            var layout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain(waterMark, 0, waterMark.length, textPaint, ViewUtils.dp2px(600f).toInt()).build()
            } else {
                StaticLayout(waterMark, textPaint, ViewUtils.dp2px(600f).toInt(),
                    Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true)
            }

            canvas.translate(50f, 50f+ScreenUtils.getStatusBarHeight(context))
            layout.draw(canvas)
            canvas.save()
            canvas.restore()

            return newMap;
        }



        /**
         * 字符拼接
         */
        fun stringSplitJoint(vararg arr: String): String {
            var str = ""
            for (i in arr.indices) {
                when (i) {
                    arr.size - 1 -> str += arr.get(i)
                    else -> str += arr.get(i) + "\n"
                }
            }
            return str
        }
    }


}