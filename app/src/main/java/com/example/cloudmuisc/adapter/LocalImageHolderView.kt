package com.example.cloudmuisc.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.example.cloudmuisc.R
import com.example.cloudmuisc.bean.BannerBean

/**
 * Created by wuhao on 2018/1/25.
 */
class LocalImageHolderView : Holder<BannerBean.BannerDetail> {
    lateinit var itemPic: ImageView
    lateinit var typeTitle: TextView

    override fun UpdateUI(context: Context?, position: Int, data: BannerBean.BannerDetail?) {
        Glide.with(context).load(data?.pic).into(itemPic)
        typeTitle.setText(data?.typeTitle?:"")
        when(data?.titleColor) {
            "red" -> {
                typeTitle.setBackgroundResource(R.drawable.shape_half_cirle_red)
            }
            "blue" -> {
                typeTitle.setBackgroundResource(R.drawable.shape_half_cirle_bule)
            }
        }
    }

    override fun createView(context: Context): View {
        val view = View.inflate(context, R.layout.item_banner, null)
        itemPic = view.findViewById(R.id.itemPic)
        typeTitle = view.findViewById(R.id.typeTitle)
        return view
    }
}