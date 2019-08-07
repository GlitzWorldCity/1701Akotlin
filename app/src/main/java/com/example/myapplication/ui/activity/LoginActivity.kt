package com.example.myapplication.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.myapplication.R
import com.example.myapplication.contract.LoginContract
import com.example.myapplication.preasenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(),LoginContract.View {

    val  presenter = LoginPresenter(this)
    override fun init() {
        super.init()
        newUser.setOnClickListener {
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            login()
        }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }
    fun login(){
        //隐藏软键盘
        hideSoftKeyBoard()
        if (hawriteExterNalStoragePermission()) {
            val userNameString = userName.text.toString().trim();
            val passWordString = password.text.toString().trim();
            presenter.login(userNameString, passWordString)
        }else applyWriteExteranISoragePermissino()
    }

    private fun applyWriteExteranISoragePermissino() {
        val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permission,0)
    }

    //检查是否写磁盘权限
    private fun hawriteExterNalStoragePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            login()
        }else Toast.makeText(this, R.string.permission_denied, LENGTH_SHORT).show()
    }

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
        Toast.makeText(this, R.string.login_failed,LENGTH_SHORT).show()
    }

    override fun getLayoutResId(): Int  = R.layout.activity_login

}
