package com.example.cloudmuisc

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.cloudmuisc.R
import com.example.cloudmuisc.adapter.SongTypeListAdapter
import com.example.cloudmuisc.databinding.ActivitySongTypeListBinding

/**
 * Created by wuhao on 2018/2/7.
 */
class SongTypeListActivity : AppCompatActivity() {

    private var selectStyle: String = "全部歌单"
    private lateinit var songTypeListBinding: ActivitySongTypeListBinding
    val data: MutableList<MutableList<String>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        songTypeListBinding = DataBindingUtil.setContentView(this, R.layout.activity_song_type_list)
        selectStyle = intent.getStringExtra("songType")
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        songTypeListBinding.mainRecycler.layoutManager = linearLayoutManager
        val songTypeListAdapter = SongTypeListAdapter(this)
        songTypeListBinding.mainRecycler.adapter = songTypeListAdapter
        if (selectStyle == "全部歌单") {
            songTypeListAdapter.setData(data,"")
            songTypeListBinding.typeAll.setBackgroundResource(R.drawable.shape_song_type_unit_select)
        }else{
            songTypeListBinding.typeAll.setBackgroundResource(R.drawable.shape_song_type_unit_no_select)
            songTypeListAdapter.setData(data,selectStyle)
        }
        songTypeListAdapter.setOnclickGridItem(object :SongTypeListAdapter.OnclickGridItemListen {
            override fun onClick(value: String) {
                if (value == "全部歌单") {
                    songTypeListBinding.typeAll.setBackgroundResource(R.drawable.shape_song_type_unit_select)
                }else {
                    songTypeListBinding.typeAll.setBackgroundResource(R.drawable.shape_song_type_unit_no_select)
                }
                songTypeListAdapter.setData(data,value)
                songTypeListAdapter.notifyDataSetChanged()
                setResult(200,Intent().putExtra("songType",value))
                finish()
            }
        })
        songTypeListAdapter.notifyDataSetChanged()
    }

    private fun initData() {

        val languageList = mutableListOf("语种", "华语", "欧美", "日语", "韩语", "粤语", "小语种")

        val styleList = mutableListOf("风格", "流行", "摇滚", "民谣", "电子", "舞曲", "说唱", "轻音乐"
                , "爵士", "乡村", "古典", "民族", "英伦", "金属", "朋克")

        val sceneList = mutableListOf("场景", "清晨", "夜晚", "学习", "工作", "午休", "下午茶", "地铁", "驾车", "运动")

        val feelingList = mutableListOf("情感", "怀旧", "清新", "浪漫", "情感", "性感", "伤怀", "治愈"
                , "放松", "孤独", "感动", "兴奋", "快乐", "安静", "思恋")
        data.add(languageList)
        data.add(styleList)
        data.add(sceneList)
        data.add(feelingList)
    }


}