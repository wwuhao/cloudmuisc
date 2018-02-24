package com.example.cloudmuisc.bean

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.cloudmuisc.BR


/**
 * Created by wuhao on 2018/1/29.
 */
data class PrivateContentBean(val string: String) {
     var code:Int? = 0
     var name:String? = null
     var result:List<PrivateContentBean.ResultBean>? = null

    class ResultBean : BaseObservable() {

        @Bindable
        var id :Float? = 0f
            set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }

        @Bindable
        var url:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.url)
            }


        @Bindable
        var picUrl:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.picUrl)
            }

        @Bindable
        var sPicUrl:String?= null
            set(value) {
                field = value
                notifyPropertyChanged(BR.sPicUrl)
            }

        @Bindable
        var type :Int? = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.type)
            }


        @Bindable
        var copywriter:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.copywriter)
            }



        @Bindable
        var name:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.name)
            }



        @Bindable
        var eventUserId:Float? = 0f
            set(value) {
                field = value
                notifyPropertyChanged(BR.eventUserId)
            }


        @Bindable
        var width:Int? = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.width)
            }

        @Bindable
        var videoId:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.videoId)
            }


        @Bindable
        var eventType:Int? = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.eventType)
            }

        @Bindable
        var alg:String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.alg)
            }

        @Bindable
        var height:Int? = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.height)
            }
    }
}