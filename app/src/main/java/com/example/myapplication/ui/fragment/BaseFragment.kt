package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.toast

abstract class BaseFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
         = inflater.inflate(getLayoutBaseId(),null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }
    open fun init(){
        //初始化公共功能，子类也可以覆写该方法，完成自己的初始化

    }
    abstract fun getLayoutBaseId(): Int
}