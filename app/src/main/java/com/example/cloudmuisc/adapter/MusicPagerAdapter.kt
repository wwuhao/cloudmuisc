package com.example.cloudmuisc.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.cloudmuisc.music.MusicListFragment
import com.example.cloudmuisc.music.RadioFragment
import com.example.cloudmuisc.music.RankFragment
import com.example.cloudmuisc.music.RecommendFragment

/**
 * Created by wuhao on 2018/1/23.
 */
class MusicPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    val titleList = arrayOf("个性推荐","歌单","主播电台","排行榜")
    val fragementList = arrayOf(RecommendFragment(),MusicListFragment(), RadioFragment(),RankFragment())

    override fun getItem(position: Int): Fragment {
        return fragementList.get(position)
    }

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList.get(position)
    }
}