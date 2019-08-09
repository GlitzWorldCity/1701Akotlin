package com.example.myapplication.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.ConversationListAdapter
import com.example.myapplication.adapter.EMMesssageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConversationFragment :BaseFragment(){

    val conversations = mutableListOf<EMConversation>()

    override fun getLayoutBaseId(): Int = R.layout.fragment_conversation

    val messageListener = object :EMMesssageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversations()
        }
    }

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        loadConversations()
    }

    private fun loadConversations() {
        doAsync {
            conversations.clear()
            val  allConversations =  EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }

    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }
    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}