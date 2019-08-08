package com.example.myapplication.preasenter

import com.example.myapplication.contract.ContactContract
import com.example.myapplication.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


class ContactPresenter (val view: ContactContract.View):ContactContract.Presenter{

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            //再次加载数据,先清空集合
            contactListItems.clear()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                //根据首字符排序
                usernames.sortBy { it[0] }
                usernames.forEachIndexed { index, s ->
                    val  shorFirsLetter = index ==0 || s[0] != usernames[index-1][0]
                    val contactListItem = ContactListItem (s,s[0].toUpperCase(),shorFirsLetter)
                    contactListItems.add(contactListItem)
                }
                uiThread { view.onLoadContactSuccess() }
            }catch (e:HyphenateException){
                uiThread { view.onLoadContactFaied() }
            }

        }
    }



}