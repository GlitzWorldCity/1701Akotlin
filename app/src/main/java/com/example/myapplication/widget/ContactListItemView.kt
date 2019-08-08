package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.myapplication.R
import com.example.myapplication.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

class ContactListItemView (context: Context?,attrs :AttributeSet?=null):RelativeLayout(context, attrs){

    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }
    fun bindView(contactListItem: ContactListItem) {
        if (contactListItem.showFrrstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contactListItem.firstLetter.toString()
        }else firstLetter.visibility = View.GONE

        userName.text = contactListItem.userName
    }

}