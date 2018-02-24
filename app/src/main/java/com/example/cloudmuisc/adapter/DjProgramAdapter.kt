package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.DjProgramBean
import com.example.cloudmuisc.databinding.ItemRecommendDjBinding

/**
 * Created by wuhao on 2018/2/3.
 */
class DjProgramAdapter: RecyclerView.Adapter<DjProgramAdapter.DjProgramHolder>() {
    var context:Context? = null
    var data:List<DjProgramBean.ResultBean>? = null

    fun setData(context:Context,data:List<DjProgramBean.ResultBean>) {
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DjProgramHolder {
        val itemRecommendDjBinding  = DataBindingUtil.inflate<ItemRecommendDjBinding>(LayoutInflater.from(context)
                , R.layout.item_recommend_dj,null,false)
        return DjProgramHolder(itemRecommendDjBinding)

    }

    override fun onBindViewHolder(holder: DjProgramHolder?, position: Int) {

        holder?.itemRecommendDjBinding?.resultBean = data?.get(position)
        holder?.itemRecommendDjBinding?.context = context


    }

    override fun getItemCount(): Int {
        if (data != null && data!!.size > 0) {
            return data!!.size
        }
        return 0
    }


    class DjProgramHolder(itemRecommendDjBinding:ItemRecommendDjBinding)
        : RecyclerView.ViewHolder(itemRecommendDjBinding.root) {

        var itemRecommendDjBinding: ItemRecommendDjBinding = itemRecommendDjBinding
    }


}