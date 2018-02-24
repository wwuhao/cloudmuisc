package com.example.cloudmuisc

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Created by wuhao on 2018/2/4.
 */

object SpUtils {

    val fileName:String = "spFile"
    val RECOMMEND_ORDER_KEY = "recommend_order_key"

    fun put(context: Context,key:String,value:String) {
        val sp = context.getSharedPreferences(fileName,MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString(key,value)
        edit.commit()
    }


    fun get(context: Context,key: String,default:String):String {
        val sp = context.getSharedPreferences(fileName,MODE_PRIVATE)
        val str = sp.getString(key,default)
        return str
    }




}