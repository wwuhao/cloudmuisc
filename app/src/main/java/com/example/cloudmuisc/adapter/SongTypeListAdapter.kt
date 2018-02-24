package com.example.cloudmuisc.adapter

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.cloudmuisc.DensityUtils.dip2px
import com.example.cloudmuisc.R

/**
 * Created by wuhao on 2018/2/7.
 */
class SongTypeListAdapter(context: Context) : RecyclerView.Adapter<SongTypeListAdapter.SongTypeListViewHolder>() {

    var context = context
    lateinit var datas: MutableList<MutableList<String>>
    private val activity: Activity = context as Activity
    private val screenWidth: Int = activity.windowManager.defaultDisplay.width
    private var onclickGridItem:OnclickGridItemListen? = null
    private var selectStyle:String? = ""

    fun setData(data: MutableList<MutableList<String>>,selectStyle:String) {
        this.datas = data
        this.selectStyle = selectStyle
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongTypeListViewHolder {
        val view = View.inflate(context, R.layout.item_song_type_list, null)
        return SongTypeListViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onBindViewHolder(holder: SongTypeListViewHolder?, position: Int) {
        val typeList = datas[position]
        holder?.gridLayoutView?.removeAllViews()
        val columnCount = holder?.gridLayoutView?.columnCount
        typeList.forEachIndexed({ index, value ->
            val childItem = LayoutInflater.from(context).inflate(R.layout.item_child_song_type_list, null)
            val contentTv = childItem.findViewById<TextView>(R.id.contentTv)
            contentTv.text = value
            val layoutParams = contentTv.layoutParams as RelativeLayout.LayoutParams
            var margin = layoutParams.marginEnd
            contentTv.width = screenWidth / columnCount!!
            holder.gridLayoutView?.addView(childItem)
            if (index == 0) {
                val layoutParams1 = childItem.layoutParams as GridLayout.LayoutParams
                layoutParams1.rowSpec = GridLayout.spec(0,2)
                layoutParams1.setGravity(Gravity.FILL)
                childItem.isEnabled = false
            }else {
                contentTv.height = dip2px(context,50f)
            }

            if (selectStyle!!.isNotEmpty() && value == selectStyle) {
                contentTv.setBackgroundResource(R.drawable.shape_song_type_unit_select)
            }


            childItem.setOnClickListener({
                _: View? ->
                onclickGridItem?.onClick(value)
            })
        })
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class SongTypeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gridLayoutView = itemView.findViewById<GridLayout>(R.id.gridLayoutView)
    }

    interface OnclickGridItemListen {
        fun  onClick(value:String)
    }

    fun setOnclickGridItem(onclickGridItem:OnclickGridItemListen) {
        this.onclickGridItem = onclickGridItem
    }

}