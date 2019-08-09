package com.example.myapplication.contract

import com.example.myapplication.BasePresenter

interface ChatContract {
    interface Presenter:BasePresenter{
        fun sendMessage(contact:String,message :String)
    }
    interface View{
        fun onStratSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}