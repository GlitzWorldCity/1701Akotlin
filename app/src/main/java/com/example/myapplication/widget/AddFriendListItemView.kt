package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.myapplication.R
import com.example.myapplication.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import kotlinx.android.synthetic.main.view_contact_item.view.*
import kotlinx.android.synthetic.main.view_contact_item.view.userName

class AddFriendListItemView (context: Context? ,attrs:AttributeSet? = null):RelativeLayout(context, attrs){
    fun bindView(addFriendItem: AddFriendItem) {
        userName.text = addFriendItem.userName
        timestamp.text = addFriendItem.timestamp
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item,this)
    }
}