package com.example.myapplication.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.adapter.EMMesssageListenerAdapter
import com.example.myapplication.factory.FragmentFactory
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    val messageListener  = object :EMMesssageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread{ undateBottonBarReadCount()}
        }
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { taskId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(taskId)!!)
            beginTransaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        EMClient.getInstance().addConnectionListener(object :EMConnectionListener{
            override fun onConnected() {


            }

            override fun onDisconnected(p0: Int) {

                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE){
                    //发生多设备登录
                    startActivity<LoginActivity>()
                    finish()
                }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
    override fun onResume() {
        super.onResume()
        undateBottonBarReadCount()
    }

    private fun undateBottonBarReadCount() {
        //初始化bottomBar未读消息个数
        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }
}
