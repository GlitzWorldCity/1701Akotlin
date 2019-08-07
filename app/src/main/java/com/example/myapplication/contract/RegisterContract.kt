package com.example.myapplication.contract

import com.example.myapplication.BasePresenter

interface RegisterContract {
    interface Presenter :BasePresenter{
        fun register(userName :String,password:String,confirmPassword :String)
    }
    interface View{
        fun onUserNameError();
        fun onPasswordError();
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccesss()
        fun onRegisterFailed()
        fun onUserExist()
    }
}