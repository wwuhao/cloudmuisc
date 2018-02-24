package com.example.cloudmuisc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cloudmuisc.SpUtils.RECOMMEND_ORDER_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yydcdut.sdlv.Menu
import com.yydcdut.sdlv.SlideAndDragListView


/**
 * Created by wuhao on 2018/2/4.
 */
class TrimOrderActivity : AppCompatActivity(), SlideAndDragListView.OnDragDropListener, View.OnClickListener {


    var orderList: MutableList<String>? = null
    lateinit var slidingListView: SlideAndDragListView
    private var titleString:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trim_order)
        slidingListView = findViewById(R.id.slidingListView)
        val regainTv = findViewById<TextView>(R.id.regainTv)
        val backIv = findViewById<ImageView>(R.id.backIv)
        regainTv.setOnClickListener(this)
        backIv.setOnClickListener(this)
        initData()
        initListView()
    }

    private fun initData() {
        val spString = SpUtils.get(this, RECOMMEND_ORDER_KEY, "")
        if (spString.isEmpty()) {
            orderList = mutableListOf("推荐歌单", "独家放送", "推荐mv", "主播电台")
            val json = Gson().toJson(orderList)
            SpUtils.put(this, RECOMMEND_ORDER_KEY, json)
        } else {
            orderList = Gson().fromJson(spString, object : TypeToken<List<String>>() {}.type)
        }
    }

    private fun initListView() {
        slidingListView.setMenu(Menu(true, 0))
        slidingListView.adapter = OrderBaseAdapter()
        slidingListView.setOnDragDropListener(this)
    }

    override fun onDragViewStart(beginPosition: Int) {
        titleString = orderList?.get(beginPosition)
    }

    override fun onDragDropViewMoved(fromPosition: Int, toPosition: Int) {
        val titleString:String = orderList?.removeAt(fromPosition)!!
         orderList?.add(toPosition, titleString)
    }

    override fun onDragViewDown(finalPosition: Int) {
        orderList?.set(finalPosition, titleString!!)
    }

    override fun onClick(v: View?) {
       when(v?.id) {
           R.id.regainTv -> {
               SpUtils.put(this@TrimOrderActivity,RECOMMEND_ORDER_KEY ,"")
               initData()
           }
           R.id.backIv -> {
               putIntent()
               finish()
           }
       }
    }

    inner class OrderBaseAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val cvh: CustomViewHolder
            var convertView1: View? = convertView
            if (convertView1 == null) {
                cvh = CustomViewHolder()
                convertView1 = View.inflate(this@TrimOrderActivity, R.layout.item_order_list, null)
                cvh.title = convertView1.findViewById(R.id.title)
                cvh.ico = convertView1.findViewById(R.id.ico)
                convertView1.tag = cvh
            } else {
                cvh = convertView1.tag as CustomViewHolder
            }
            cvh.title.text = orderList?.get(position)
            cvh.ico.setOnTouchListener(mOnTouchListener)
            cvh.ico.tag = position
            return convertView1
        }

        private val mOnTouchListener = View.OnTouchListener { v, event ->
            val o = v.tag
            if (o != null && o is Int) {
                Log.d("TrimOrderActivity","mOnTouchListener:+ ${o.toInt()}")
                slidingListView.startDrag(o.toInt())
            }
            false
        }

        override fun getItem(position: Int): Any? {
            return orderList?.get(position)
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return if (orderList != null && orderList!!.size > 0) {
                orderList!!.size
            } else {
                0
            }
        }

        inner class CustomViewHolder {
            lateinit var title: TextView
            lateinit var ico:ImageView
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            putIntent()
        }
        return super.onKeyDown(keyCode, event)
    }

    fun putIntent() {
        var orderListString:String = Gson().toJson(orderList)
        var intent =  Intent()
        intent.putExtra("orderExtra",orderListString)
        SpUtils.put(this@TrimOrderActivity,RECOMMEND_ORDER_KEY,orderListString)
        this.setResult(0,intent)
    }

}