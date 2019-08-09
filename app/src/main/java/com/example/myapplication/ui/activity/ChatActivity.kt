package com.example.myapplication.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.adapter.EMMesssageListenerAdapter
import com.example.myapplication.adapter.MessageListAapter
import com.example.myapplication.contract.ChatContract
import com.example.myapplication.preasenter.ChatPreseter
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.view_send_message_item.*
import org.jetbrains.anko.toast

class ChatActivity :BaseActivity(),ChatContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_chat
    lateinit var username :String
    val presenter = ChatPreseter(this)
    val messageListAapter = object : EMMesssageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMEssage(username,p0)
            runOnUiThread {
                recyclerView.adapter?.notifyDataSetChanged()
                scrollToBootom()
            }
        }
    }
    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRectclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListAapter)
        send.setOnClickListener { send() }
    }
    private fun initRectclerView(){
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =MessageListAapter(context,presenter.messages)
        }
    }

    fun send(){
        hideSoftKeyBoard()
        val message = edit.text.trim().toString()
        presenter.sendMessage(username,message)
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //如果用户输入的长度大于0，发送按钮是enable
                send.isEnabled = !s.isNullOrEmpty()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        edit.setOnEditorActionListener { v, actionId, event ->
            send()
            true
        }
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        //获取聊天用户名
        username = intent.getStringExtra("username")
        val titleString = String.format(getString(R.string.chat_title),username)
        headerTitle.text = titleString
    }

    override fun onStratSendMessage() {
        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        edit.text.clear()
        scrollToBootom()
    }
    private fun scrollToBootom(){

        recyclerView.scrollToPosition(presenter.messages.size-1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListAapter)
    }
}
