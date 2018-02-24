package com.example.cloudmuisc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.cloudmuisc.Api.baseApi
import com.example.cloudmuisc.adapter.PlayListAdapter
import com.example.cloudmuisc.bean.SongDetailBean
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wuhao on 2018/2/9.
 */
class PlayListActivity : AppCompatActivity() {

    lateinit var mainRecyclerView: RecyclerView
    lateinit var adapter: PlayListAdapter
    lateinit var data: MutableList<SongDetailBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)

        mainRecyclerView = findViewById(R.id.mainRecycler)
        initRecyclerView()
        val id = intent.getStringExtra("playId")
        data = mutableListOf()
        getData(id)

    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlayListAdapter()
        mainRecyclerView.adapter = adapter
    }

    private fun getData(id: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseApi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getPlayList(id)
                .subscribeOn(Schedulers.io())
                .concatMap({ playListBean ->
                    Observable.fromIterable(playListBean.playlist?.trackIds)
                })
                .observeOn(Schedulers.io())
                .concatMap({ trackIdsBean ->
                    Log.d("PlayListActivity","id:" + trackIdsBean.id?.toString())
                    getSongDetail(trackIdsBean.id?.toString())
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ songDetailBean ->
                    Log.d("PlayListActivity","id:" + Gson().toJson(songDetailBean))
                    if (songDetailBean.songs != null && songDetailBean.songs!!.isNotEmpty()) {
                        data.add(songDetailBean)
                    }
                }, {}, {
                    adapter.setData(this, data)
                    adapter.notifyDataSetChanged()
                })
    }

    private fun getSongDetail(id: String?): Observable<SongDetailBean> {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseApi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getSongDetail(id)
    }
}






