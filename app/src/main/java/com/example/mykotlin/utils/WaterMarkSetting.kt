package com.example.mykotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class WaterMarkSetting {

    companion object {

        fun saveAndCompress(
            bitmap: Bitmap,
            waterMark: String,
            context: Context,
            savePath: String,
            compressPath: String,
            success: (path: String) -> Unit
        ) {
            var isOk = save(createWatermarkBitMap(bitmap, waterMark, context), savePath)
            if (isOk) {
                compress(savePath, compressPath, context, success)
            }
        }

        fun createWatermarkBitMap(bitmap: Bitmap, waterMark: String, context: Context): Bitmap {
            var result = bitmap.copy(bitmap.config, true)
            val canvas = Canvas(result)
            var paint = Paint()
            paint.isAntiAlias = true

            //画水印文本
            var textPaint = TextPaint()
            textPaint.setARGB(0xFF, 0xff, 0, 0)
            textPaint.textSize = ViewUtils.dp2px(16f) * bitmap.width / getScreenWidth(context)
            textPaint.textAlign = Paint.Align.LEFT

            var layout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain(
                    waterMark,
                    0,
                    waterMark.length,
                    textPaint,
                    ViewUtils.dp2px(600f).toInt()
                ).build()
            } else {
                StaticLayout(
                    waterMark, textPaint, ViewUtils.dp2px(600f).toInt(),
                    Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true
                )
            }
            var textOffsetX = (bitmap.width / 20).toFloat()
            var textOffsetY = (bitmap.height / 20).toFloat()

            canvas.translate(textOffsetX, textOffsetY)
            layout.draw(canvas)

            return result
        }

        fun save(bitmap: Bitmap, savePath: String): Boolean {
            if (bitmap == null || bitmap.width == 0 || bitmap.height == 0)
                return false
            FileUtils.createOrExistsFile(savePath)
            var outPut = BufferedOutputStream(FileOutputStream(File(savePath)))
            return bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outPut)
        }

        /**
         * 图片压缩
         */
        fun compress(
            savePath: String,
            compressPath: String,
            context: Context,
            success: (path: String) -> Unit
        ) {
            FileUtils.createOrExistsDir(compressPath)
            Luban.with(context)
                .load(File(savePath))
                .ignoreBy(100)
                .setTargetDir(compressPath)
                .setFocusAlpha(false)
                .setCompressListener(object : OnCompressListener {
                    override fun onSuccess(file: File?) {
                        if (FileUtils.isFile(savePath)) {
                            FileUtils.delete(savePath)
                        }
                        file?.path?.let { success(it) }
                    }

                    override fun onError(e: Throwable?) {
                        success(savePath)
                    }

                    override fun onStart() {
                    }

                }).launch()
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