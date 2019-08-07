package com.example.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.myapplication.contract.RegisterContract
import com.example.myapplication.preasenter.RegisterPreasenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity() ,RegisterContract.View{

    val presenter = RegisterPreasenter(this)
    override fun init() {
        super.init()
        register.setOnClickListener { register() }
        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            register()
            true
        }
    }
    fun register(){
        hideSoftKeyBoard()
        val userNameString = userName.text.trim().toString();
        val passwordString = password.text.trim().toString();
        val confirmPasswordString = confirmPassword.text.trim().toString();
        presenter.register(userNameString,passwordString,confirmPasswordString)
    }


    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccesss() {
        dissmissProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed() {
        dissmissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        dissmissProgress()
        toast(R.string.user_already_exist)
    }
    override fun getLayoutResId(): Int = R.layout.activity_register


}
