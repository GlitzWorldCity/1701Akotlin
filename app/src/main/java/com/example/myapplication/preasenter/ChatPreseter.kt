package com.example.myapplication.preasenter

import com.example.myapplication.adapter.EMCallBackAdapter
import com.example.myapplication.contract.ChatContract
import com.hyphenate.chat.EMCallManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

class ChatPreseter(val view: ChatContract.View) :ChatContract.Presenter{
    val messages = mutableListOf<EMMessage>()
    override fun sendMessage(contact: String, message: String) {
        val emMessage = EMMessage.createTxtSendMessage(message,contact)
        emMessage.setMessageStatusCallback(object :EMCallBackAdapter(){
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {

                uiThread { view.onSendMessageFailed()}
            }
        })
        messages.add(emMessage)
        view.onStratSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

}