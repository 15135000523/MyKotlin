package com.example.mykotlin.utils

import android.content.res.Resources
import android.util.TypedValue

class ViewUtils {
    companion object {
        fun dp2px(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().displayMetrics
            )
        }
    }
}