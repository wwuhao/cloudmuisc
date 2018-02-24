package com.example.cloudmuisc.bean

/**
 * Created by wuhao on 2018/2/9.
 */
class SongDetailBean {

    var code:Int? = null
    var songs:List<SongsBean>? = null
    class SongsBean {
        var id:Int? = null
        var mv:Float? = null
        var name:String? = null
        var al:AlBean? = null
        var ar:List<ArBean>? = null
    }

    class AlBean {
        var id:Float? = null
        var name:String? = null
    }

    class ArBean {
        var id:Float? = null
        var name:String? = null
    }

}