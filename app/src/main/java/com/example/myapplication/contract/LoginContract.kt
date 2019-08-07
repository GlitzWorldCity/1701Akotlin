package com.example.myapplication.contract

import com.example.myapplication.BasePresenter

interface LoginContract{
    interface Presenter : BasePresenter {
        fun login(userName: String, passWord: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLoging()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}