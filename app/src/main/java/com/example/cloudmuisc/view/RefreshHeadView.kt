package com.example.cloudmuisc.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.cloudmuisc.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

/**
 * Created by wuhao on 2018/2/7.
 */
class RefreshHeadView(context: Context) : LinearLayout(context), RefreshHeader {
    var tagIv: ImageView
    lateinit var animationDrawable:AnimationDrawable

    init {
        val view = View.inflate(context, R.layout.layout_refresh_view, null)
        tagIv = view.findViewById(R.id.tagIv)
        gravity = Gravity.CENTER
        addView(view)
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, extendHeight: Int) {
        animationDrawable = tagIv.drawable as AnimationDrawable
        animationDrawable.start()
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        animationDrawable.stop()
        return 2000
    }


    override fun onStateChanged(refreshLayout: RefreshLayout?, oldState: RefreshState?, newState: RefreshState?) {
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
    }

    override fun onReleased(refreshLayout: RefreshLayout?, height: Int, extendHeight: Int) {
    }

    override fun onPulling(percent: Float, offset: Int, height: Int, extendHeight: Int) {
    }

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onReleasing(percent: Float, offset: Int, height: Int, extendHeight: Int) {
    }

    override fun isSupportHorizontalDrag(): Boolean {

        return false
    }


}