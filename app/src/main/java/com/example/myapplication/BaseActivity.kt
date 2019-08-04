package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }
    open fun init() {

    }


    abstract fun getLayoutResId(): Int
}