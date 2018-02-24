package com.example.cloudmuisc.music

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by wuhao on 2018/1/23.
 */
class RadioFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var textView = TextView(context)
        textView.setText("主播电台")

        return textView
    }

}