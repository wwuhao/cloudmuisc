package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.SongDetailBean
import com.example.cloudmuisc.databinding.ItemPalyListBinding

/**
 * Created by wuhao on 2018/2/9.
 */
class PlayListAdapter : RecyclerView.Adapter<PlayListAdapter.PlayListViewHold>() {

    var data1: List<SongDetailBean>? = null
    var context: Context? = null

    fun setData(context: Context, data: List<SongDetailBean>?) {
        this.data1 = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlayListViewHold {
        val itemPlayListBinding = DataBindingUtil.inflate<ItemPalyListBinding>(LayoutInflater.from(context)
                , R.layout.item_paly_list, null, false)
        return PlayListViewHold(itemPlayListBinding)
    }

    override fun onBindViewHolder(holder: PlayListViewHold?, position: Int) {
        var songDetailBean = data1!![position]
        var position :Int  = position
        val songsBean = songDetailBean.songs!![0]
        holder?.itemPlayListBinding?.songsBean = songsBean
        holder?.itemPlayListBinding?.position = position + 1
    }

    override fun getItemCount(): Int {
        return if (data1 != null && data1!!.isNotEmpty()) {
            data1!!.size
        } else {
            0
        }
    }

    class PlayListViewHold(itemPlayListBinding: ItemPalyListBinding) : RecyclerView.ViewHolder(itemPlayListBinding.root) {
        var itemPlayListBinding: ItemPalyListBinding = itemPlayListBinding
    }
}