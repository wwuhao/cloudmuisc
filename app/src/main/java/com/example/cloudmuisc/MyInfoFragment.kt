package com.example.cloudmuisc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * Created by wuhao on 2018/1/20.
 */
class MyInfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var textView = TextView(context)
        textView.setText("我的个人信息")
        return textView
    }

}