package com.example.myapplication.factory

import android.support.v4.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.fragment.ContactFragment
import com.example.myapplication.ui.fragment.ConversationFragment
import com.example.myapplication.ui.fragment.DynamicFragment

class FragmentFactory private constructor(){

    val conversation by lazy {
        ConversationFragment()
    }
    val contract by lazy {
        ContactFragment()
    }
    val dynamic by lazy {
        DynamicFragment()
    }
    companion object{
        val instance = FragmentFactory()
    }
    fun getFragment(tabId :Int):Fragment?{
        when(tabId){
            R.id.tab_conversation -> return conversation
            R.id.tab_contacts -> return contract
            R.id.tab_dynamic -> return dynamic
        }
        return null
    }
}