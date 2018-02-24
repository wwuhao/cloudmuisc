package com.example.cloudmuisc

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by wuhao on 2018/1/29.
 */
class RecommendItemDecoration() : RecyclerView.ItemDecoration() {
    var space:Int? = 0

    constructor(space:Int) : this() {
        this.space = space
    }


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.left = space
        outRect?.top = space
        outRect?.bottom = space
        outRect?.right = space
    }
}