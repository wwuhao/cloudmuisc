package com.example.cloudmuisc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var transaction: FragmentTransaction
    lateinit var myInfoFragment: Fragment
    lateinit var musicFragment: Fragment
    lateinit var myMusicFragment: Fragment
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivMenu.setOnClickListener(this)

        ivMusic.setOnClickListener(this)
        ivMenu.setOnClickListener(this)
        ivMusic.setOnClickListener(this)

        initView()
    }

    private fun initView() {
        transaction = supportFragmentManager.beginTransaction()
        musicFragment = MusicFragment()
        myMusicFragment = MyMusicFragment()
        myInfoFragment = MyInfoFragment()

        transaction.add(R.id.homeFrameLayout,musicFragment, "music")
        transaction.add(R.id.homeFrameLayout,myMusicFragment, "my")
        transaction.add(R.id.homeFrameLayout,myInfoFragment, "mine")
        hideAll(transaction)
        transaction.commit()

        tabSelectFragment(0)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivMenu -> {
                Log.d(TAG,"onclic --> menu")
                homeDrawer.openDrawer(Gravity.START)
            }
            R.id.ivMusic -> {
                Log.d(TAG,"onclic --> MusicFragment")
                tabSelectFragment(0)
            }

            R.id.ivMyMusic -> {
                Log.d(TAG,"onclic --> myMusicFragment")
                tabSelectFragment(1)}

            R.id.ivMineInfo -> {
                Log.d(TAG,"onclic --> myInfoFragment")
                tabSelectFragment(2)
            }
        }
    }

    private fun tabSelectFragment(index: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        hideAll(transaction)
        when (index) {
            0 -> transaction.show(musicFragment)
            1 -> transaction.show(myMusicFragment)
            2 -> transaction.show(myInfoFragment)
        }
        transaction.commit()
    }

    private fun hideAll(transaction: FragmentTransaction) {
        transaction.hide(musicFragment)
        transaction.hide(myMusicFragment)
        transaction.hide(myInfoFragment)
    }
}



