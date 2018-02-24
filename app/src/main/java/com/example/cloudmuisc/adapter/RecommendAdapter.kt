package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.RecommendItemBean
import com.example.cloudmuisc.databinding.ItemRecommendBinding

/**
 * Created by wuhao on 2018/1/25.
 */
class RecommendAdapter: RecyclerView.Adapter<RecommendAdapter.RecommendHolder>() {
    var data:List<RecommendItemBean.ResultBean>? = null
    var context:Context? = null
    val Tag:String = "RecommendAdapter"

    fun setData(context:Context ,data:List<RecommendItemBean.ResultBean>?) {
        this.data = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecommendHolder {
        var layoutflater = LayoutInflater.from(parent?.context)
        ItemRecommendBinding.inflate(layoutflater)

        var itemRecommendBinding = DataBindingUtil.inflate<ItemRecommendBinding>(layoutflater,R.layout.item_recommend,null,false)
        return RecommendHolder(itemRecommendBinding)
    }

    override fun onBindViewHolder(holder: RecommendHolder?, position: Int) {
        var resultBean =  data?.get(position)
        holder?.viewDataBinding?.resultBean = resultBean
        Log.d(Tag,resultBean?.picUrl)
    }

    override fun getItemCount(): Int {
        if (data != null && data!!.size > 0 && data!!.size > 6) {
            return 6
        }else if (data != null && data!!.size > 0 && data!!.size < 6)  {
            return data!!.size
        }else {
            return 0
        }
    }

    class RecommendHolder(viewDataBinding : ViewDataBinding): RecyclerView.ViewHolder(viewDataBinding?.root) {
            var viewDataBinding:ItemRecommendBinding = viewDataBinding as ItemRecommendBinding
    }

}