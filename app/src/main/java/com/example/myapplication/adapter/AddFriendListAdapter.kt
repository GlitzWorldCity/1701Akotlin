package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.data.AddFriendItem
import com.example.myapplication.widget.AddFriendListItemView

class AddFriendListAdapter(val context: Context,val addFriendItems: MutableList<AddFriendItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = addFriendItems.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val addFriendListItemView = p0.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[p1])
    }

    class AddFriendListItemViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView!!)
}