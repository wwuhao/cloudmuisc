package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.RecommendMvBean
import com.example.cloudmuisc.databinding.ItemRecommendMvBinding

/**
 * Created by wuhao on 2018/2/1.
 */
class RecommendMvAdapter : RecyclerView.Adapter<RecommendMvAdapter.NewMusicHolder>() {

    var context:Context? = null
    var data:List<RecommendMvBean.ResultBean>? = null
    fun setData(context:Context,data:List<RecommendMvBean.ResultBean>?) {
        this.context = context
        this.data = data
    }

    override fun onBindViewHolder(holder: NewMusicHolder?, position: Int) {
        holder?.itemRecommendMvBinding?.resultBean = data?.get(position)
    }

    override fun getItemCount(): Int {
        if (data != null && data!!.isNotEmpty()) {
            return data!!.size
        }else {
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewMusicHolder {
        var itemRecommendMvBinding = DataBindingUtil.inflate<ItemRecommendMvBinding>(LayoutInflater.from(context)
                , R.layout.item_recommend_mv,null,false)
        return NewMusicHolder(itemRecommendMvBinding)
    }


    class NewMusicHolder(itemRecommendMvBinding:ItemRecommendMvBinding): RecyclerView.ViewHolder(itemRecommendMvBinding.root) {
        var itemRecommendMvBinding:ItemRecommendMvBinding? = null
        init {
            this.itemRecommendMvBinding = itemRecommendMvBinding
        }
    }
}