package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat.startActivity

class SpashActivity : BaseActivity(),SpashContract.View {

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
