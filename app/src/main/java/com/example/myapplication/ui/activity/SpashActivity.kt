package com.example.myapplication.ui.activity

import android.content.Intent
import android.os.Handler
import com.example.myapplication.R
import com.example.myapplication.SpashContract
import com.example.myapplication.SplashPresenter

class SpashActivity : BaseActivity(), SpashContract.View {

    val presenter = SplashPresenter(this)

    companion object{
        val DELAY = 2000L
    }
    val handler by lazy {
        Handler()
    }
    override fun init (){
        super.init()
        presenter.checkLoginStatus()
    }
    override fun getLayoutResId(): Int = R.layout.activity_splash

    //延时2s，跳转到登录界面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            var intent = Intent(this@SpashActivity, LoginActivity::class.java)
            startActivity(intent)
        }, DELAY)
    }

    override fun onLoggedIn() {
            var intent = Intent(this@SpashActivity, MainActivity::class.java)
            startActivity(intent)
        finish()
    }
}
