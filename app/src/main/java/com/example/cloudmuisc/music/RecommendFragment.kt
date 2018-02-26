package com.example.cloudmuisc.music

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.example.cloudmuisc.*
import com.example.cloudmuisc.Api.baseApi
import com.example.cloudmuisc.adapter.*
import com.example.cloudmuisc.bean.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by wuhao on 2018/1/23.
 */
class RecommendFragment : Fragment(), OnItemClickListener, View.OnClickListener {

    private lateinit var convenientBanner: ConvenientBanner<BannerBean.BannerDetail>
    private lateinit var recommendAdapter: RecommendAdapter
    private lateinit var contentLayout: LinearLayout
    private lateinit var privateAdapter:PrivateContentAdapter
    private lateinit var mvAdapter: RecommendMvAdapter
    private lateinit var djAdapter:DjProgramAdapter
    private lateinit var viewMap:MutableMap<String,View>
    private var orderList: MutableList<String>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_recommend, null)
        convenientBanner = view?.findViewById(R.id.convenientBanner)!!
        contentLayout = view.findViewById(R.id.contentLayout)
        val orderTv = view.findViewById<TextView>(R.id.orderTv)
        orderTv.setOnClickListener(this)
        viewMap = mutableMapOf()
        initBanner()
        initBodyView()
        return view
    }

    private fun initBanner() {
        var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApi)
                .build()

        var apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ banner: BannerBean ->
                    setHeaderAdapter(banner.banners)
                })
    }

    private fun setHeaderAdapter(data: List<BannerBean.BannerDetail>) {
        convenientBanner.setPages({ LocalImageHolderView() }, data)
                .setPageIndicator(intArrayOf(R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused))
                .setOnItemClickListener(this)
    }

    private fun initBodyView() {
        initRecommendSongList()
        initPrivateContent() //独家放送
        initRecommendMv() //推荐mv
        intiDjProgram()  //主播电台
        addView()
    }

    private fun addView() {
        orderList = mutableListOf("推荐歌单", "独家放送", "推荐mv", "主播电台")
        val orderListString = SpUtils.get(context, SpUtils.RECOMMEND_ORDER_KEY,Gson().toJson(orderList))
        var orderList: MutableList<String> = Gson().fromJson(orderListString,object : TypeToken<List<String>>() {}.type)
        orderView(orderList)
    }

    private fun orderView(orderList: MutableList<String>) {
        contentLayout.removeAllViews()
        orderList.forEach {
          val view = viewMap[it]
            if (view != null) {
                contentLayout.addView(view)
            }
       }
    }

    private fun initRecommendSongList() {
        val recommendSong = context.viewInflate(R.layout.layout_item_recommend)
        viewMap.put("推荐歌单",recommendSong)
        val itemTitle = recommendSong.findViewById<TextView>(R.id.itemTitle)
        val songListRecycler = recommendSong.findViewById<RecyclerView>(R.id.itemRecyclerView)
        val recommendListMore = recommendSong.findViewById<TextView>(R.id.recommendListMore)
        recommendListMore.setOnClickListener(this)
        songListRecycler.layoutManager = object:GridLayoutManager(context,3) {
            override fun canScrollVertically(): Boolean {
                return false
            }

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        songListRecycler.addItemDecoration(RecommendItemDecoration(10))
        itemTitle.text = getString(R.string.recommendSong)
        recommendAdapter = RecommendAdapter()
        songListRecycler.adapter = recommendAdapter
        getRecommendSongData()
        contentLayout.addView(recommendSong)
    }

    private fun initPrivateContent() {
        val privateContentView = context.viewInflate(R.layout.layout_item_recommend)
        viewMap.put("独家放送",privateContentView)
        val itemTitle = privateContentView.findViewById<TextView>(R.id.itemTitle)
        itemTitle.text = getString(R.string.privateContent)
        val privateRecycler = privateContentView.findViewById<RecyclerView>(R.id.itemRecyclerView)
        var layoutManager = object :GridLayoutManager(context,2) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        privateRecycler.layoutManager = layoutManager
        privateRecycler.addItemDecoration(RecommendItemDecoration(10))
        layoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                Log.d("RecommendFragment","SpanSizeLookup:position = "+position)
                if (position == 2) {
                    return 2
                }
                return 1
            }
        }
        privateAdapter = PrivateContentAdapter()
        privateRecycler.adapter = privateAdapter
        getPrivateContentData()
        contentLayout.addView(privateContentView)
    }

    private fun initRecommendMv() {
        val mvView = context.viewInflate(R.layout.layout_item_recommend)
        viewMap.put("推荐mv",mvView)
        val itemTitle = mvView.findViewById<TextView>(R.id.itemTitle)
        itemTitle.text = getString(R.string.recommendMv)
        val newMusicRecycler = mvView.findViewById<RecyclerView>(R.id.itemRecyclerView)
        newMusicRecycler.layoutManager = object :GridLayoutManager(context,2) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        newMusicRecycler.addItemDecoration(RecommendItemDecoration(10))
        mvAdapter = RecommendMvAdapter()
        newMusicRecycler.adapter = mvAdapter
        getRecommendMv()
        contentLayout.addView(mvView)
    }


    private fun intiDjProgram() {
        val djView = context.viewInflate(R.layout.layout_item_recommend)
        viewMap.put("主播电台",djView)
        val itemTitle = djView.findViewById<TextView>(R.id.itemTitle)
        itemTitle.text = getString(R.string.recommendDj)
        val newMusicRecycler = djView.findViewById<RecyclerView>(R.id.itemRecyclerView)
        newMusicRecycler.layoutManager = object :GridLayoutManager(context,2) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        newMusicRecycler.addItemDecoration(RecommendItemDecoration(10))
         djAdapter = DjProgramAdapter()
        newMusicRecycler.adapter = djAdapter
        getDjProgramData()
        contentLayout.addView(djView)
    }

    private fun getRecommendMv() {
        var retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseApi)
                .build()
        var apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getMv()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe {
            recommendMv: RecommendMvBean ->
            if (recommendMv.code == 200) {
                mvAdapter.setData(context,recommendMv.result)
                mvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getPrivateContentData() {
        var retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseApi)
                .build()
        var ApiInterface = retrofit.create(ApiInterface::class.java)
        ApiInterface.getPivateContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    privateContentBean: PrivateContentBean ->
                    if (privateContentBean.code == 200) {
                        privateAdapter.setData(context,privateContentBean.result)
                        privateAdapter.notifyDataSetChanged()
                    }
                }
    }

    /*获取推荐歌单列表数据*/
    private fun getRecommendSongData() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApi)
                .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        apiInterface.getPersonalized()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
            recommendItemBean: RecommendItemBean ->
            Log.d("RecommendFragment","getRecommendSongData RecommendItemBean: "
                    + Gson().toJson(recommendItemBean))
            if (recommendItemBean.code == 200) {
                recommendAdapter.setData(context, recommendItemBean.result)
                recommendAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getDjProgramData() {

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApi)
                .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        apiInterface.getDjprogram()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    djProgramBean: DjProgramBean ->
                    Log.d("RecommendFragment","getRecommendSongData RecommendItemBean: "
                            + Gson().toJson(djProgramBean))
                    if (djProgramBean.code == 200) {
                        djAdapter.setData(context, djProgramBean.result)
                        djAdapter.notifyDataSetChanged()
                    }
                }
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.orderTv ->  {
                startActivityForResult(Intent(context,TrimOrderActivity::class.java),200)
            }
            //推荐歌单更多
            R.id.recommendListMore -> {
                startActivity(Intent(context, SongListDetailActivity::class.java))
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 200 && resultCode == 0) {
            var orderListString = data?.getStringExtra("orderExtra")
            var  orderList = Gson().fromJson<MutableList<String>>(orderListString,object : TypeToken<MutableList<String>>() {}.type)
            orderView(orderList)
        }
    }

    override fun onResume() {
        super.onResume()
        convenientBanner.startTurning(2000)
    }

    override fun onPause() {
        super.onPause()
        convenientBanner.stopTurning()
    }

    override fun onItemClick(position: Int) {

    }

}


