package com.example.myapplication.ui.fragment

import android.content.Intent
import com.example.myapplication.R
import com.example.myapplication.adapter.EMCallBackAdapter
import com.example.myapplication.ui.activity.LoginActivity
import com.example.myapplication.ui.activity.RegisterActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DynamicFragment :BaseFragment(){
    override fun getLayoutBaseId(): Int = R.layout.fragment_dynamic
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)

        val logoutString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)
        logout.text = logoutString

        logout.setOnClickListener {
            logout()
        }
    }
    fun logout(){
        EMClient.getInstance().logout(true,object :EMCallBackAdapter(){
            override fun onSuccess() {
                context?.runOnUiThread {
                    toast(R.string.logout_success)
                    startActivity<LoginActivity>()
                    var intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }

            }

            override fun onError(p0: Int, p1: String?) {
                context?.runOnUiThread { toast(R.string.logout_failed) }
            }
        })

    }
}