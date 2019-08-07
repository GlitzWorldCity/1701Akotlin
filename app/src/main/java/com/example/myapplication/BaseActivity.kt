package com.example.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(){
    val progressDiaLog by lazy {
        ProgressDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }
    open fun init() {

    }


    abstract fun getLayoutResId(): Int

    fun showProgress(message:String){
        progressDiaLog.setMessage(message)
        progressDiaLog.show()
    }
    fun dissmissProgress(){
        progressDiaLog.dismiss()
    }
}