package com.example.myapplication


class SplashPresenter (val view :SpashContract.View):SpashContract.Presenter{
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn()else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false

}