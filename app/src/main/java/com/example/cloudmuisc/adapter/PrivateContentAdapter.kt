package com.example.cloudmuisc.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.PrivateContentBean
import com.example.cloudmuisc.databinding.ItemHomePrivteContentBinding

/**
 * Created by wuhao on 2018/1/30.
 */
class PrivateContentAdapter : RecyclerView.Adapter<PrivateContentAdapter.PrivateContentHolder>() {

    var datas:List<PrivateContentBean.ResultBean>? = null
    var context:Context? = null

    fun setData(context:Context,data:List<PrivateContentBean.ResultBean>?) {
        this.context = context
        this.datas = data
    }

    override fun getItemCount(): Int {
        if (datas!=null && datas!!.isNotEmpty()) {
            return datas!!.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PrivateContentHolder {
        var itemHomePrivateContentBinding = DataBindingUtil.inflate<ItemHomePrivteContentBinding>(LayoutInflater.from(parent?.context)
                , R.layout.item_home_privte_content,null,false)
        return PrivateContentHolder(itemHomePrivateContentBinding)
    }

    override fun onBindViewHolder(holder: PrivateContentHolder?, position: Int) {
        var resultBean = datas?.get(position)
        holder?.itemHomePrivateContentBinding?.resultBean = resultBean
        holder?.itemHomePrivateContentBinding?.context= context
    }

    class PrivateContentHolder(dataBinding:ItemHomePrivteContentBinding?): RecyclerView.ViewHolder(dataBinding?.root) {
        var itemHomePrivateContentBinding:ItemHomePrivteContentBinding? = null
        init {
            this.itemHomePrivateContentBinding = dataBinding
        }
    }

}