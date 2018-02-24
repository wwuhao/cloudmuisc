package com.example.cloudmuisc

import android.content.Context

/**
 * Created by wuhao on 2018/2/7.
 */
object  DensityUtils {

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }
}