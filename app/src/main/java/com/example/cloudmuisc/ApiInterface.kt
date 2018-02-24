package com.example.cloudmuisc

import com.example.cloudmuisc.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wuhao on 2018/1/24.
 */
interface ApiInterface {

    @GET("banner")
    fun getBanner():Observable<BannerBean>

    /*获取推荐歌单数据*/
    @GET("personalized")
    fun getPersonalized():Observable<RecommendItemBean>

    /*独家放送*/
    @GET("personalized/privatecontent")
    fun getPivateContent():Observable<PrivateContentBean>

    @GET("personalized/mv")
    fun getMv():Observable<RecommendMvBean>


    @GET("personalized/djprogram")
    fun getDjprogram():Observable<DjProgramBean>

    @GET("playlist/detail?")
    fun  getPlayList(@Query("id") id:String):Observable<PlayListBean>

    @GET("/song/detail?")
        fun getSongDetail(@Query("ids") ids:String?):Observable<SongDetailBean>
}