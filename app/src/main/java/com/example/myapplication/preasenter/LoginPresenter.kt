package com.example.myapplication.preasenter

import com.example.myapplication.contract.LoginContract
import com.example.myapplication.extentions.isValiUserName
import com.example.myapplication.extentions.isValipassword
import com.hyphenate.chat.EMClient
import com.example.myapplication.adapter.EMCallBackAdapter


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
        EMClient.getInstance().login(userName, passWord, object : EMCallBackAdapter() {

            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //在主线程通知view层
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoggedInFailed() }
            }
        })
    }
}