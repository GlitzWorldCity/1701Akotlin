package com.example.myapplication.app

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager.STREAM_MUSIC
import android.media.SoundPool
import cn.bmob.v3.Bmob
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.adapter.EMMesssageListenerAdapter
import com.example.myapplication.ui.activity.ChatActivity
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.cloud.CloudFileManager.instance

class IMAppLication :Application(){
    companion object{
        lateinit var instance :IMAppLication
    }

    val  soundPool = SoundPool(2,STREAM_MUSIC,0)

    val duan by lazy {
        soundPool.load(instance, R.raw.duan,0)
    }
    val yulu by lazy {
        soundPool.load(instance,R.raw.yulu,0)
    }
    val messageListener = object :EMMesssageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果在前台，则勃发短的声音
            if (isForegroud()){
                soundPool.play(duan,1f,1f,0,0,1f)
            }else{
                soundPool.play(yulu,1f,1f,0,0,1f)
                showNotification(p0)
            }

        }
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT){
                contentText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
//            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)
            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT)
            val notification = Notification.Builder(this)
                .setContentTitle(getString(R.string.receive_new_message))
                .setContentText(contentText)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.avatar1))
                .setSmallIcon(R.mipmap.ic_contact)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .notification
            notificationManager.notify(1,notification)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(this, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(applicationContext,"ed00313d9390e16d5613146962c18bf2")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }
    private fun isForegroud() : Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = activityManager.runningAppProcesses
        for (runningAppProcesses in activityManager.runningAppProcesses){
            if (runningAppProcesses.processName == packageName){
               return runningAppProcesses.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }
}