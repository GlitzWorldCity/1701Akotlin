package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.widget.ReceiveMessageItemView
import com.example.myapplication.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

class MessageListAapter(val context:Context,val message :List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (message[position].direct() == EMMessage.Direct.SEND){
            return ITEM_TYPE_SEND_MESSAGE
        }else{
            return ITEM_TYE_RECEIVE_MESSAGE
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if (p1 == ITEM_TYPE_SEND_MESSAGE){
            return SendMessageViewHolder(SendMessageItemView(context))
        }else return ReceiveMessageViewHolder(ReceiveMessageItemView(context))

    }

    override fun getItemCount(): Int = message.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val  showTimestamp = isShowTimestamp(p1)

        if (getItemViewType(p1)== ITEM_TYPE_SEND_MESSAGE){
            val sendMessageItemView = p0.itemView as SendMessageItemView
            sendMessageItemView.bindView(message[p1],showTimestamp)
        }else if(getItemViewType(p1)== ITEM_TYE_RECEIVE_MESSAGE){
            val receiveMessageItemView = p0.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(message[p1],showTimestamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        //如果是第一跳消息或者和前一天消息间隔时间比较长
        var showTimestamp = true
        if (position > 0){
            showTimestamp = !DateUtils.isCloseEnough(message[position].msgTime,message[position - 1].msgTime)
        }
        return showTimestamp
    }

    class SendMessageViewHolder(itemView:View?) :RecyclerView.ViewHolder(itemView!!){

    }
    class ReceiveMessageViewHolder(itemView:View?) :RecyclerView.ViewHolder(itemView!!){

    }
}