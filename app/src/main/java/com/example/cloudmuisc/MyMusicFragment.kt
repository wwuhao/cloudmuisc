package com.example.cloudmuisc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by wuhao on 2018/1/20.
 */
class MyMusicFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater?.inflate(R.layout.fragement_music, null)
        return view!!
    }
}


