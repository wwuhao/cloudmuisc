package com.example.cloudmuisc.bean

/**
 * Created by wuhao on 2018/2/9.
 */
class SongUrlBean {

    var code:Int? = null
    var data:MutableList<DataBean>? = null

    class DataBean {
        var br:Float? = null
        var canExtend:Boolean? = null
        var code:Int? = null
        var expi:Int? = null
        var fee:Int? = null
        var flag:Int? = null
        var gain:Float? = null
        var id:Float? = null
        var md5:String? = null
        var payed:Int? = null
        var size:Float? = null
        var type:String? = null
        var url:String? = null
    }
}