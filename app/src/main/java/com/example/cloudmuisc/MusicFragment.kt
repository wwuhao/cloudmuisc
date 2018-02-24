package com.example.cloudmuisc

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cloudmuisc.adapter.MusicPagerAdapter


/**
 * Created by wuhao on 2018/1/20.
 */
class MusicFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragement_music,null)
        val adapter = MusicPagerAdapter(fragmentManager)
        var homePager = view?.findViewById<ViewPager>(R.id.homePager)
        var homeTab = view?.findViewById<TabLayout>(R.id.homeTab)

        homePager!!.adapter = adapter

        homeTab!!.setupWithViewPager(homePager)

        return view!!
    }

}