package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.RecommendItemBean
import com.example.cloudmuisc.databinding.ItemSongListBinding

/**
 * Created by wuhao on 2018/2/6.
 */
class SongListAdapter: RecyclerView.Adapter<SongListAdapter.SongListHolder>() {

    var context:Context? = null
    var data:List<RecommendItemBean.ResultBean>? = null
    var onclickSongItemListen1:OnclickSongItemListen? = null

    fun setData(context:Context,data:List<RecommendItemBean.ResultBean>?) {
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongListHolder {
        val itemSongListBinding =  DataBindingUtil.inflate<ItemSongListBinding>(LayoutInflater.from(context)
                , R.layout.item_song_list
                ,null
                ,false)
        return SongListHolder(itemSongListBinding)
    }

    override fun onBindViewHolder(holder: SongListHolder?, position: Int) {
        var resultBean =  data!![position]
        holder?.itemSongListBinding?.resultBean = resultBean
        holder?.itemSongListBinding?.root?.setOnClickListener({
            v: View? ->
            onclickSongItemListen1?.onClickSongItem(resultBean.id)
        })

    }

    override fun getItemCount(): Int {
        return if (data != null && data!!.isNotEmpty()) {
            data!!.size
        }else {
            0
        }
    }

    class SongListHolder(itemSongListBinding:ItemSongListBinding): RecyclerView.ViewHolder(itemSongListBinding.root) {
        val itemSongListBinding = itemSongListBinding
    }

    interface OnclickSongItemListen {
        fun onClickSongItem(id:Int)
    }

    fun setOnclickSongItemListen(onclickSongItemListen:OnclickSongItemListen) {
       this.onclickSongItemListen1 = onclickSongItemListen
    }
}