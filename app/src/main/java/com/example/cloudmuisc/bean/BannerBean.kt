package com.example.cloudmuisc.bean

/**
 * Created by wuhao on 2018/1/24.
 */
data class BannerBean(var string: String){
    lateinit var code:String
    lateinit var banners:List<BannerDetail>

    class BannerDetail {
        lateinit var encodeId:String
        var exclusive = false
        lateinit var pic:String
        lateinit var targetType:String
        lateinit var titleColor:String
        lateinit var typeTitle:String
        lateinit var url:String
    }
}