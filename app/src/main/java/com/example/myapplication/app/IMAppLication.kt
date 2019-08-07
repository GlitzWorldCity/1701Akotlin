package com.example.myapplication.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.example.myapplication.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.cloud.CloudFileManager.instance

class IMAppLication :Application(){

    override fun onCreate() {
        super.onCreate()
        //初始化
        EMClient.getInstance().init(this, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(applicationContext,"ed00313d9390e16d5613146962c18bf2")

    }

}