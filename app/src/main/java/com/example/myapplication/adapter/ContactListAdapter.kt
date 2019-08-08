package com.example.myapplication.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.ContactListItem
import com.example.myapplication.ui.activity.ChatActivity
import com.example.myapplication.widget.ContactListItemView
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactListAdapter(val context: Context,val contactListItems: MutableList<ContactListItem>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int  = contactListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val contactListItenView = holder.itemView as ContactListItemView
        contactListItenView.bindView(contactListItems[position])
        val userName = contactListItems[position].userName
        contactListItenView.setOnClickListener{context.startActivity<ChatActivity>("username" to userName) }
        contactListItenView.setOnLongClickListener {
            String.format(context.getString(R.string.delete_friend_message),userName)
            AlertDialog.Builder(context)
                .setTitle(R.string.delete_friend_title)
                .setMessage(R.string.delete_friend_message)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener{ dialogInterface ,i  ->
                    deleteFriend(userName)
                }).show()
            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName,object : EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.delete_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {

                context.runOnUiThread { toast(R.string.delete_friend_failed) }
            }
        });
    }

    class ContactListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

    }
}