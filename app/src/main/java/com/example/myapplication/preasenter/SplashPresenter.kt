package com.example.myapplication

import com.hyphenate.chat.EMClient


class SplashPresenter (val view :SpashContract.View):SpashContract.Presenter{
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn()else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean =
            EMClient.getInstance().isConnected&&EMClient.getInstance().isLoggedInBefore

}