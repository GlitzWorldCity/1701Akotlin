package com.example.myapplication.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

abstract class BaseActivity:AppCompatActivity(){
    val progressDiaLog by lazy {
        ProgressDialog(this)
    }
    val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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
    fun hideSoftKeyBoard(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }
}