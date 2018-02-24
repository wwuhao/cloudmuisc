package com.example.cloudmuisc.bean

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.cloudmuisc.BR

/**
 * Created by wuhao on 2018/1/26.
 */
data class RecommendItemBean(val str:String): BaseObservable() {
    var hashTaste:String = ""
    var code:Int = 0
    var category:Int = 0
    @Bindable
    var result:List<ResultBean>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.result)
        }

    inner class ResultBean : BaseObservable() {
        @Bindable
        var id:Int = 0
            set(id) {
                field = id
                notifyPropertyChanged(BR.id)
            }

        @Bindable
        var type:Int = 0
            set(type) {
                field = type
                notifyPropertyChanged(BR.type)
            }

        @Bindable
        var name:String? = " "
            set(name) {
                field = name
                notifyPropertyChanged(BR.name)
            }

        @Bindable
        var copywriter:String? = ""
            set(copywriter) {
                field = copywriter
                notifyPropertyChanged(BR.copywriter)
            }

        @Bindable
        var picUrl:String = ""
            set(picUrl) {
                field = picUrl
                notifyPropertyChanged(BR.picUrl)
            }

        @Bindable
        var conDislike:String = ""
            set(conDislike) {
                field = conDislike
                notifyPropertyChanged(BR.conDislike)
            }


        @Bindable
        var playCount:Float = 0f
            set(playCount) {
                field = playCount
                notifyPropertyChanged(BR.playCount)
            }

        @Bindable
        var trackCount:Int = 0
            set(trackCount) {
                field = trackCount
                notifyPropertyChanged(BR.trackCount)
            }

        @Bindable
        var highQuality:Boolean = false
            set(highQuality) {
                field = highQuality
                notifyPropertyChanged(BR.highQuality)
            }

        @Bindable
        var alg:String? = ""
            set(alg) {
                field = alg
                notifyPropertyChanged(BR.alg)
            }
    }

    override fun toString(): String {
        return "RecommendItemBean(str='$str', hashTaste='$hashTaste', code=$code, category=$category, result=$result)"
    }




}