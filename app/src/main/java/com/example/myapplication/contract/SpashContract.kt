package com.example.myapplication

interface SpashContract{

    interface Presenter:BasePresenter{
        //检查登录状态
        fun checkLoginStatus ()
    }

    interface View {
        //没有登录UI的处理
        fun onNotLoggedIn()
        //已经登录UI的处理
        fun onLoggedIn()

        fun init()
    }
}