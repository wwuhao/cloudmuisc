package com.example.cloudmuisc.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * Created by wuhao on 2018/1/29.
 */
object BindingManger {

    @BindingAdapter("app:url")
    @JvmStatic
    fun setLoadImage(imageView: ImageView,url:String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    @BindingAdapter("app:imageViewId","app:radius")
    @JvmStatic
    fun setNativeImage(imageView: ImageView,imageViewId:Int,radius:Int) {
        Glide.with(imageView.context)
                .load(imageViewId)
                .bitmapTransform(BlurTransformation(imageView.context,23,4))
                .into(imageView)
    }


}


