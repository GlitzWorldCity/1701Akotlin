package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.widget.ContactListItenView
import java.text.ParsePosition

class ContactListAdapter(val context: Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItenView(context))
    }

    override fun getItemCount(): Int  = 30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){

    }
    class ContactListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

    }


}