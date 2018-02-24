package com.example.cloudmuisc.bean

/**
 * Created by wuhao on 2018/2/9.
 */
class PlayListBean {
    var code: Int? = null
    var playlist: PlaylistBean? = null

    class PlaylistBean {
        var adType: Int? = 0
        var commentCount: Int = 0
        var commentThreadId: String? = null
        var coverImgId: Float? = null
        var coverImgId_str: String? = null
        var coverImgUrl: String? = null
        var createTime: Float? = null
        var creator: CreatorBean? = null
        var description: String? = null
        var highQuality: Boolean? = null
        var id: Float? = null
        var name: String? = null
        var newImported: Boolean? = null
        var ordered: Boolean? = null
        var playCount: Int? = null
        var privacy: Int? = null
        var shareCount: Float? = null
        var specialType: Float? = null
        var status: Float? = null
        var subscribed: Boolean? = null
        var subscribedCount: Float? = null
        var trackCount: Int? = null
        var trackIds: List<TrackIdsBean>? = null
    }
    class TrackIdsBean {
        var id: String? = null
        var v: Float? = null
    }


    class CreatorBean {
        var accountStatus:Int? = null
        var authStatus:Int? = null
        var authority:Int? = null
        var avatarImgId:Float? = null
        var avatarImgIdStr:String? = null
        var avatarImgId_str:String? = null
        var avatarUrl:String? = null
        var backgroundImgId:Float? = null
        var backgroundImgIdStr:String? = null
        var backgroundUrl:String? = null
        var birthday:Float? = null
        var city:Float? = null
        var defaultAvatar:Boolean? = null
        var description:String? = null
        var followed:Boolean? = null
        var gender:Int? = null
        var nickname:String? = null
        var province:Float? = null
        var signature:String? = null
        var userId:Int? = null
        var userType:Int? = null
        var vipType:Int? = null
    }
}