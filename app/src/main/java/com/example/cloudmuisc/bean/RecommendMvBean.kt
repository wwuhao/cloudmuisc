package com.example.cloudmuisc.bean

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.cloudmuisc.BR

/**
 * Created by wuhao on 2018/2/1.
 */
data class RecommendMvBean(val string: String) {

    var code: Int? = 0
    var category: Int? = 0
    var result: List<ResultBean>? = null

    class ResultBean : BaseObservable() {

        @Bindable
        var id: Float? = 0f
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

        @Bindable
        var type: Int? = 0
            set(value) {
                field = value
                notifyPropertyChanged(BR.type)
            }


        @Bindable
        var name: String? = null

            set(value) {
                field = value
                notifyPropertyChanged(BR.name)
            }



        @Bindable
        var copywriter: String? = null

            set(value) {
                field = value
                notifyPropertyChanged(BR.copywriter)
            }


        @Bindable
        var picUrl: String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.picUrl)
            }


        @Bindable
        var canDislike: Boolean? = null

            set(value) {
                field = value
                notifyPropertyChanged(BR.canDislike)
            }


        @Bindable
        var duration: Float? = 0f
            set(value) {
                field = value
                notifyPropertyChanged(BR.duration)
            }


        @Bindable
        var playCount: Float? = 0f
            set(value) {
                field = value
                notifyPropertyChanged(BR.playCount)
            }


        @Bindable
        var subed: Boolean? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.subed)
            }


        @Bindable
        var artistName: String? = null

            set(value) {
                field = value
                notifyPropertyChanged(BR.artistName)
            }

        @Bindable
        var artistId: Float? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.artistId)
            }


        @Bindable
        var alg: String? = null

            set(value) {
                field = value
                notifyPropertyChanged(BR.alg)
            }

        @Bindable
        var artists: List<ArtistsBean>? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.artists)
            }
    }


    class ArtistsBean {
        var id: Float? = 0f
        var name: String? = null
    }


}