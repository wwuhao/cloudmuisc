package com.example.cloudmuisc.music

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.example.cloudmuisc.*
import com.example.cloudmuisc.adapter.SongListAdapter
import com.example.cloudmuisc.bean.RecommendItemBean
import com.example.cloudmuisc.databinding.ActivitySongListDetailBinding
import com.example.cloudmuisc.view.RefreshHeadView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wuhao on 2018/2/6.
 */
class SongListDetailActivity : AppCompatActivity(), SongListAdapter.OnclickSongItemListen, View.OnClickListener {

    private lateinit var binding: ActivitySongListDetailBinding
    private lateinit var songListAdapter: SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SongListDetailActivity, R.layout.activity_song_list_detail)
        initView()
        intiData()
    }

    private fun initView() {
        binding.toolbarLayout.backIv.setOnClickListener(this)
        binding.toolbarLayout.toolbarTitle.text = "歌单"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.mainRecycler.layoutManager = gridLayoutManager
        songListAdapter = SongListAdapter()
        songListAdapter.setOnclickSongItemListen(this)
        binding.mainRecycler.adapter = songListAdapter

        binding.mainRecycler.addItemDecoration(RecommendItemDecoration(10))
        binding.smartRefresh.setOnRefreshListener {
            Handler().postDelayed({
                binding.smartRefresh.finishRefresh()
            }, 200)
        }
        binding.smartRefresh.setRefreshHeader(RefreshHeadView(this))
        binding.smartRefresh.setHeaderHeight(60f)
        binding.typeAll.setOnClickListener({
            val intent = Intent(this@SongListDetailActivity, SongTypeListActivity::class.java)
            intent.putExtra("songType", binding.typeAll.text.trim())
            startActivityForResult(intent, 200)
        })
    }

    private fun intiData() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.baseApi)
                .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        apiInterface.getPersonalized()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { recommendItemBean: RecommendItemBean ->
                    Log.d("RecommendFragment", "getRecommendSongData RecommendItemBean: "
                            + Gson().toJson(recommendItemBean))
                    if (recommendItemBean.code == 200) {
                        songListAdapter.setData(this, recommendItemBean.result)
                        songListAdapter.notifyDataSetChanged()
                    }
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 200 && resultCode == 200) {
            val selectString = data?.getStringExtra("songType")
            binding.typeAll.text = selectString
        }

    }

    override fun onClickSongItem(id: Int) {
        val intent = Intent(this,PlayListActivity::class.java)
        intent.putExtra("playId",id.toString())
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backIv ->
                finish()
        }
    }

}