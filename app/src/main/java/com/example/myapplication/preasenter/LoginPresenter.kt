package com.example.myapplication.preasenter

import com.example.myapplication.contract.LoginContract
import com.example.myapplication.extentions.isValiUserName
import com.example.myapplication.extentions.isValipassword

class LoginPresenter(val view: LoginContract.View) :LoginContract.Presenter{

    override fun login(userName: String, passWord: String) {
        if (userName.isValiUserName()){
            //用户名合法
            if (passWord.isValipassword()){
                view.onStartLoging()
                LoginEaseMob(userName,passWord)

            }else view.onPasswordError()
        }else view.onUserNameError()
    }
    private fun LoginEaseMob(userName: String, passWord: String) {

    }
}