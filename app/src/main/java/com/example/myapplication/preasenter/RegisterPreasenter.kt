package com.example.myapplication.preasenter

import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.myapplication.contract.RegisterContract
import com.example.myapplication.extentions.isValiUserName
import com.example.myapplication.extentions.isValipassword
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

class RegisterPreasenter(val view: RegisterContract.View) :RegisterContract.Presenter{
    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValiUserName()){
            //检查密码
            if (password.isValipassword()){
                if (password.equals(confirmPassword)){
                    //密码相同
                    view.onStartRegister()
                    //开始注册
                    registerBmob(userName,password)
                }else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)
        bu.signUp<BmobUser>(object :SaveListener<BmobUser>(){
            override fun done(s :BmobUser?,e :BmobException?){
                if (e==null){
                    //注册成功

                    //注册到环信
                    registerEaseMob(userName,password)

                }else{
                    //注册失败
                    if (e.errorCode==202)view.onUserExist()
                    view.onRegisterFailed()
                }
            }
        })
    }

    private fun registerEaseMob(userName: String, password: String) {
        doAsync{
            try {
                //注册失败会抛出异常
                EMClient.getInstance().createAccount(userName,password)
                //注册成功
                uiThread {view.onRegisterSuccesss() }
            }catch (e:HyphenateException){
                //注册失败
                uiThread { view.onRegisterFailed() }
            }
        }

    }

}