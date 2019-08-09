package com.example.myapplication.contract

import com.example.myapplication.BasePresenter
import com.hyphenate.chat.EMMessage

interface ChatContract {
    interface Presenter:BasePresenter{
        fun sendMessage(contact:String,message :String)
        abstract fun addMEssage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessages(username: String)
        fun loadMoreMessages(username: String)
    }
    interface View{
        fun onStratSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMoreMessageLoaded(size: Int)
        fun onMessageLoaded()
    }
}