package com.example.myapplication.contract

import com.example.myapplication.BasePresenter
import com.hyphenate.chat.EMMessage

interface ChatContract {
    interface Presenter:BasePresenter{
        fun sendMessage(contact:String,message :String)
        abstract fun addMEssage(username: String, p0: MutableList<EMMessage>?)
    }
    interface View{
        fun onStratSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}