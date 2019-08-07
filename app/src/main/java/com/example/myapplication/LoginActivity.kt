package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.myapplication.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity :BaseActivity(),LoginContract.View {
    override fun onUserNameError() {
        userName.setError(getString(R.string.user_name_error))
    }

    override fun onPasswordError() {
        password.setError(getString(R.string.password_error))
    }

    override fun onStartLoging() {
        //弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        //隐藏进度条
        dissmissProgress()
        //进入住界面
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        //LoginAotivity
        finish()
    }

    override fun onLoggedInFailed() {
        //隐藏进度条
        dissmissProgress()
        //弹出toast
        Toast.makeText(this,R.string.login_failed,LENGTH_SHORT).show()
    }

    override fun getLayoutResId(): Int  = R.layout.activity_login

}
