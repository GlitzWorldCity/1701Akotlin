package com.example.myapplication.app

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.cloud.CloudFileManager.instance
import java.lang.Exception
import kotlin.math.E

class IMAppLication :Application(){

    override fun onCreate() {
        super.onCreate()
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);

    }

}