package com.example.cloudmuisc.adapter

import android.content.Context
import android.view.View

/**
 * Created by wuhao on 2018/1/25.
 */
fun Context.viewInflate(id:Int):View {
    return View.inflate(this,id,null)
}